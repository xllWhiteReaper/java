package programming_2.bnf_set_calculator.src;

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
        // parseInputAndGetResultingSet("null");

        Set<Integer> resultingSet = operateSets(
                new TreeSet<>(List.of(1, 2, 3, 4)),
                new TreeSet<>(List.of(3, 4, 5)),
                "-");
        for (int integer : resultingSet) {
            System.out.println("Integer in resulting set: " + integer);
        }

        // Set<Integer> set1 = getIntegerSetFromStringRepresentation(
        // " [1, 2, -7 , 4,4,8,,,,0,5,6, 7,] ");

        // for (Integer integer : set1) {
        // System.out.println("Number in the set:" + integer + ":end");
        // }
    }

    public static Set<Integer> getIntegerSetFromStringRepresentation(String inputSetStringRepresentation)
            throws NonNegativeArgument, IncorrectSetSyntaxException {
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
        inputString = inputString.trim();
        if (!inputString.startsWith("[") || !inputString.endsWith("]")) {
            throw new IncorrectSetOperationSyntaxException(
                    "The set operation have to start with \"[\" and end with \"]\"");
        }
        Indexes[] elementsIndexes = new Indexes[3]; // will contain indexes for the first set, operator and last set

        return new TreeSet<Integer>(List.of(1, 2, 3, 4));
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
