package programming_2.spell_checker.src;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import programming_2.spell_checker.models.SpellChecker;

public class App {
    private final static SpellChecker spellChecker = new SpellChecker();

    public static void main(String[] args) {
        readUserFileAndDetectWronglySpelledWords();
    }

    private static void readUserFileAndDetectWronglySpelledWords() {
        Set<String> misspelledWords = new TreeSet<String>();
        File userFile = getInputFileNameFromUser();
        if (userFile == null) {
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
            // TODO: handle exception
        }

        for (String misspelledWord : misspelledWords) {
            System.out.println("Misspelled word: " + misspelledWord);
            System.out.println(
                    "Suggestions: " + String.join(", ", spellChecker.getPossibleCorrectSpellings(misspelledWord)));
            System.out.println();
            System.out.println();
        }
    }

    /**
     * Lets the user select an input file using a standard file
     * selection dialog box. If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
    static File getInputFileNameFromUser() {
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
