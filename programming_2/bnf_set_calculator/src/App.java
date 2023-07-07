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

import programming_2.bnf_set_calculator.models.IncorrectSetSyntaxException;
import programming_2.bnf_set_calculator.models.NonNegativeArgument;

public class App {
    public static final Scanner Scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Set<Integer> set1 = getIntegerSetFromStringRepresentation(
                "    [1, 2,  -7 , 4,4,8,,,,0,5,6,    7,]   ");

        for (Integer integer : set1) {
            System.out.println("Number in the set:" + integer + ":end");
        }
    }

    public static Set<Integer> getIntegerSetFromStringRepresentation(String inputSetStringRepresentation)
            throws NonNegativeArgument, IncorrectSetSyntaxException {
        inputSetStringRepresentation = inputSetStringRepresentation.trim();
        int trimmedLength = inputSetStringRepresentation.length();
        String[] trimmedInputString = inputSetStringRepresentation.substring(1, trimmedLength - 1).split(",");
        Set<Integer> integerSet = new TreeSet<>();
        for (String numberString : trimmedInputString) {
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

    public static Set<Integer> parseInputAndGetResultingSet(String inputString) throws IncorrectSetSyntaxException {
        inputString = inputString.trim();
        return new TreeSet<Integer>(List.of(1, 2, 3, 4));
    }
}
