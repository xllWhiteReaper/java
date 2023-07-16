package programming_2.spell_checker.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import programming_2.spell_checker.models.SpellChecker;
import programming_2.spell_checker.utils.FileHandler;

public class App {
    private final static SpellChecker spellChecker = new SpellChecker();

    public static void main(String[] args) {
        try {
            spellChecker.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("The path provided for the CORRECTLY_SPELLED_WORDS .txt file wasn't found");
            return;
        }
        readUserFileAndDetectWronglySpelledWords();
    }

    private static void readUserFileAndDetectWronglySpelledWords() {
        Set<String> misspelledWords = new TreeSet<String>();
        File userFile = getInputFileFromUser();

        if (userFile == null) {
            System.out.println("The user didn't select a file, closing...");
            return;
        }

        if (FileHandler.getFileType(userFile) != "text/plain") {
            System.out.println("The user didn't select a text file, closing...");
            return;
        }

        try (Scanner fileReader = new Scanner(userFile)) {
            fileReader.useDelimiter("[^a-zA-Z]+");
            while (fileReader.hasNext()) {
                String word = fileReader.next().toLowerCase();
                if (!spellChecker.isCorrectlySpelled(word)) {
                    misspelledWords.add(word);
                }
            }
        } catch (Exception e) {
            System.out.println("Sorry, the file couldn't be opened successfully, please try again!");
            return;
        }

        for (String misspelledWord : misspelledWords) {
            final Set<String> possibleCorrectSpellings = spellChecker.getPossibleCorrectSpellings(misspelledWord);
            final String suggestions = possibleCorrectSpellings.isEmpty() ? "(no suggestions available)"
                    : String.join(", ", possibleCorrectSpellings);
            System.out.println("Misspelled word: " + misspelledWord);
            System.out.println(
                    "Suggestions: " + suggestions);
            System.out.println();
            System.out.println();
        }
    }

    /**
     * Lets the user select an input file using a standard file
     * selection dialog box. If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
    private static File getInputFileFromUser() {
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Select File for Input");
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileDialog.setFileFilter(filter);
        int option = fileDialog.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION)
            return null;
        else
            return fileDialog.getSelectedFile();
    }
}
