package programming_2.turing_machine.src;

import programming_2.turing_machine.src.turing.TestTape;
import programming_2.turing_machine.src.turing.TestTapeGUI;
import programming_2.turing_machine.src.turing.TestTuringMachine;

public class App {
    public static void main(String[] args) {
        // // WORKS
        // System.out.println("TEST 1: TestTape");
        // TestTape.test(args);
        // printLines(2);

        // DOESN'T WORK
        System.out.println("TEST 2: TestTapeGUI");
        TestTapeGUI.test(args);
        printLines(2);

        // // WORKS
        // System.out.println("TEST 3: TestTuringMachine");
        // TestTuringMachine.test(args);
        // printLines(2);
    }

    private static void printLines() {
        printLines(1);
    }

    private static void printLines(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }
}
