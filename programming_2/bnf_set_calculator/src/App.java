package programming_2.bnf_set_calculator.src;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
// v2, would be interesting <set-result> := <set> <operator> <set> | <set-result> <operator> <set>
// <set-result> := <set> <operator> <set> | <set>
// v2
// <set-operation>:= <set> <operator> <set>
// <set> := "[" <set-element> "]"
// <set-element> := <number> | <set-element> "," <number> | null
// <operator> := + | * | -
// <number> := 0 | 1 | ... | 9 | <number><number>
import java.util.Set;
import java.util.TreeSet;

import javax.naming.LimitExceededException;

import programming_2.bnf_set_calculator.models.IncorrectSetOperationSyntaxException;
import programming_2.bnf_set_calculator.models.IncorrectSetSyntaxException;
import programming_2.bnf_set_calculator.models.Indexes;
import programming_2.bnf_set_calculator.models.NonNegativeArgument;

public class App {
    public static final Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Set<Integer> resultingSet = operateSets(
        // new TreeSet<>(List.of(1, 2, 3, 4)),
        // new TreeSet<>(List.of(3, 4, 5)),
        // "-");
        // for (int integer : resultingSet) {
        // System.out.println("Integer in resulting set: " + integer);
        // }

        Set<Integer> resultingSet = parseInputAndGetResultingSet("     [1,2,3,45  ] + [6,   8,10, 20]");

        StringBuilder sb = new StringBuilder("[");
        Iterator<Integer> iterator = resultingSet.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        String resultingSetStringRepresentation = sb.toString();

        System.out.println("Resulting set: " + resultingSetStringRepresentation);
        // System.out.print("Resulting set: [ ");
        // for (int integer : resultingSet) {
        // // if(integer== resultingSet){}
        // System.out.print(integer + ", ");
        // }
        // System.out.print(" ]");

        // getIntegerSetFromStringRepresentation("[1, 24,67]");

        // parseInputAndGetResultingSet("null");

        // Set<Integer> set1 = getIntegerSetFromStringRepresentation(
        // " [1, 2, -7 , 4,4,8,,,,0,5,6, 7,] ");

        // for (Integer integer : set1) {
        // System.out.println("Number in the set:" + integer + ":end");
        // }
    }

    public static Set<Integer> getIntegerSetFromStringRepresentation(String inputSetStringRepresentation)
            throws NonNegativeArgument, IncorrectSetSyntaxException {
        if (inputSetStringRepresentation == null) {
            throw new IllegalArgumentException("The input can not be null");
        }
        inputSetStringRepresentation = inputSetStringRepresentation.trim();
        if (!inputSetStringRepresentation.startsWith("[") || !inputSetStringRepresentation.endsWith("]")) {
            throw new IncorrectSetSyntaxException("Sets have to start with \"[\" and have to end with \"]\"");
        }
        int trimmedLength = inputSetStringRepresentation.length();
        String[] trimmedInputStrings = inputSetStringRepresentation.substring(1, trimmedLength - 1).split(",");
        Set<Integer> integerSet = new TreeSet<>();
        for (String numberString : trimmedInputStrings) {
            String trimmedNumber = numberString.trim();
            if (trimmedNumber == "") {
                continue;
            }
            int parsedInt = -1;
            try {
                parsedInt = Integer.parseInt(trimmedNumber);
            } catch (NumberFormatException e) {
                throw new IncorrectSetSyntaxException(
                        "The set can not contain other characters other than positive numbers");
            }
            if (parsedInt < 0) {
                throw new NonNegativeArgument("The set can only contain positive numbers");
            }
            integerSet.add(parsedInt);
        }
        return integerSet;
    }

    public static Set<Integer> parseInputAndGetResultingSet(String inputString)
            throws IncorrectSetOperationSyntaxException {
        if (inputString == null) {
            throw new IllegalArgumentException("Input can not be null");
        }
        inputString = inputString.trim();
        int trimmedInputLength = inputString.length();
        if (!inputString.startsWith("[") || !inputString.endsWith("]")) {
            throw new IncorrectSetOperationSyntaxException(
                    "The set operation has to start with \"[\" and end with \"]\"");
        }
        Indexes[] elementsIndexes = new Indexes[3]; // will contain indexes for the first set, operator and last set
        elementsIndexes[0] = new Indexes(0, inputString.indexOf("]", 0));
        if (elementsIndexes[0].end() == inputString.length() - 1) {
            throw new IncorrectSetOperationSyntaxException(
                    "The set operation has to have an operator");
        }
        elementsIndexes[2] = new Indexes(inputString.indexOf("[", elementsIndexes[0].end() + 1),
                trimmedInputLength - 1);
        if (elementsIndexes[2].start() == -1) {
            throw new IncorrectSetOperationSyntaxException(
                    "The operation is missing a \"[\" symbol");
        }
        elementsIndexes[1] = new Indexes(elementsIndexes[0].end() + 1, elementsIndexes[2].start() - 1);
        if (elementsIndexes[1].end() <= elementsIndexes[1].start()) {
            throw new IncorrectSetOperationSyntaxException(
                    "The set operation has to have an operator");
        }
        Set<Integer> set1;
        Set<Integer> set2;
        String operator = inputString.substring(elementsIndexes[1].start(), elementsIndexes[1].end() + 1).trim();

        try {
            set1 = getIntegerSetFromStringRepresentation(
                    inputString.substring(elementsIndexes[0].start(), elementsIndexes[0].end() + 1));

            set2 = getIntegerSetFromStringRepresentation(
                    inputString.substring(elementsIndexes[2].start(), elementsIndexes[2].end() + 1));
        } catch (IncorrectSetSyntaxException e) {
            throw new IncorrectSetOperationSyntaxException(e.getMessage());
        } catch (NonNegativeArgument e) {
            throw new IncorrectSetOperationSyntaxException(e.getMessage());
        }
        if (operator.equals("")) {
            throw new IncorrectSetOperationSyntaxException("The set operation has to have an operator");
        }
        return operateSets(set1, set2, operator);
        // return new TreeSet<Integer>(List.of(1, 2, 3, 4));
    }

    private static Set<Integer> operateSets(Set<Integer> set1, Set<Integer> set2, String operator)
            throws IllegalArgumentException {
        if (set1 == null || set2 == null) {
            throw new IllegalArgumentException("Sets can not be null");
        }
        if (operator == null || operator.equals("")) {
            throw new IllegalArgumentException("Operator can not be empty");
        }
        switch (operator) {
            case "+":
                set1.addAll(set2);
                return set1;

            case "*":
                set1.retainAll(set2);
                return set1;

            case "-":
                set1.removeAll(set2);
                return set1;

            default:
                throw new IllegalArgumentException("There is no such operation as \"" + operator + "\" defined");
        }
    }
}
