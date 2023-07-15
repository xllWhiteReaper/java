package programming_2.spell_checker.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellChecker {
    private final Set<String> DICTIONARY;
    private final String WORDS_FILE_PATH = "programming_2/spell_checker/resources/words.txt";

    public SpellChecker() {
        DICTIONARY = new HashSet<String>();
        readFile();
    }

    private void readFile() {
        try (Scanner fileIn = new Scanner(new File(WORDS_FILE_PATH))) {
            while (fileIn.hasNext()) {
                String word = fileIn.next();
                addWordToDictionary(word);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Wrong path");
            e.printStackTrace();
        }
    }

    private void addWordToDictionary(String word) {
        DICTIONARY.add(word.toLowerCase());
    }

    public long getDictionarySize() {
        return DICTIONARY.size();
    }
}
