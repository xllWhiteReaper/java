package programming_2.spell_checker.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import programming_2.spell_checker.utils.CharIterator;

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

    public boolean isCorrectlySpelled(String wordToCheck) {
        return DICTIONARY.contains(wordToCheck);
    }

    public Set<String> getPossibleCorrectSpellings(String wronglySpelledWord) {
        Set<String> possibleCorrectSpellings = new TreeSet<String>();
        // possibleCorrectSpellings.addAll(getSimilarWordsByDeletion(wronglySpelledWord));
        possibleCorrectSpellings.addAll(getSimilarWordsByReplacing(wronglySpelledWord));

        return possibleCorrectSpellings;
    }

    private Set<String> getSimilarWordsByDeletion(String word) {
        Set<String> similarWords = new TreeSet<String>();
        for (int i = 0; i < word.length(); i++) {
            String wordWithDeletedCharacter = word.substring(0, i) + word.substring(i + 1);
            if (isCorrectlySpelled(wordWithDeletedCharacter)) {
                similarWords.add(wordWithDeletedCharacter);
            }
        }
        return similarWords;
    }

    private Set<String> getSimilarWordsByReplacing(String word) {
        Set<String> similarWords = new TreeSet<String>();
        CharIterator charIterator;
        for (int i = 0; i < word.length(); i++) {
            charIterator = new CharIterator();
            while (charIterator.hasNext()) {
                String wordWithReplacedCharacter = word.substring(0, i) + charIterator.next() + word.substring(i + 1);
                if (isCorrectlySpelled(wordWithReplacedCharacter)) {
                    similarWords.add(wordWithReplacedCharacter);
                }
            }
        }
        return similarWords;
    }
}
