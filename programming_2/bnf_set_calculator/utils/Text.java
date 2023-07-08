package programming_2.bnf_set_calculator.utils;

public class Text {
    public static void printNLines(int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            System.out.println();
        }
    }

    public static void printSeparation(char c) {
        int width = 50;
        for (int i = 0; i < width; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void printSeparation() {
        printSeparation('=');
    }
}
