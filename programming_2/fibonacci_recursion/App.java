package programming_2.fibonacci_recursion;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int index = -1;
        while (index < 0) {
            try {
                System.out.println("Please enter a sequence index for the fibonacci sequence");
                index = scanner.nextInt();
                if (index < 0) {
                    System.out.println("Only positive numbers allowed");
                }
            } catch (Exception e) {
                index = -1;
                System.out.println("Please enter just whole numbers");
                scanner.nextLine();
            }
        }

        System.out.println("fibonacci for sequence at index " + index + " = " + getFibonacciForIndex(index));

        scanner.close();
    }

    public static int getFibonacciForIndex(int index) {
        return index < 0 ? 0
                : index == 0 ? 1 : index == 1 ? 1 : getFibonacciForIndex(index - 1) + getFibonacciForIndex(index - 2);
    }
}
