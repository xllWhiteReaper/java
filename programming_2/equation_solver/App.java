package programming_2.equation_solver;

import java.util.Scanner;

public class App {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String option = "";
        while (!option.equals("no")) {
            System.out.println("Welcome to the app");
            System.out.println("To help you solve the root of a quadratic equation,");
            System.out
                    .println("Please provide the values of a, b, and c by providing the numbers separated by a space");
            System.out.println("e.g. '13 2 6'");
            String[] numbersAsString = scanner.nextLine().trim().split(" ");
            int A, B, C;
            try {
                A = Integer.parseInt(numbersAsString[0]);
                B = Integer.parseInt(numbersAsString[1]);
                C = Integer.parseInt(numbersAsString[2]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, the number of arguments is wrong");
                option = askIfUserWantsToContinue();
                continue;
            } catch (NumberFormatException e) {
                System.out.println("The format that you provided wasn't adequate");
                option = askIfUserWantsToContinue();
                continue;
            }
            try {
                double[] roots = findRoots(A, B, C);
                System.out.println("The roots for the equation are " + roots[0] + " and " + roots[1]);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() != null ? e.getMessage()
                        : "The roots can not be found for the specified numbers");
                option = askIfUserWantsToContinue();
                continue;
            }
            option = askIfUserWantsToContinue();
        }
        scanner.close();
    }

    private static double[] findRoots(double A, double B, double C)
            throws IllegalArgumentException {
        if (A == 0) {
            throw new IllegalArgumentException("A can't be zero.");
        } else {
            double disc = B * B - 4 * A * C;
            if (disc < 0)
                throw new IllegalArgumentException("Discriminant < zero.");
            double discriminatoryRoot = Math.sqrt(disc);
            double[] roots = { (-B + discriminatoryRoot) / (2 * A), (-B - discriminatoryRoot) / (2 * A) };
            return roots;
        }
    }

    private static String askIfUserWantsToContinue() {
        System.out.println("Do you want to continue using the program? If not, type no");
        return scanner.nextLine().toLowerCase().trim();
    }
}