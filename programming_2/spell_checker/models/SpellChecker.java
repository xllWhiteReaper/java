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
    private final String CORRECTLY_SPELLED_WORDS_FILE_PATH = "programming_2/spell_checker/resources/words.txt";

    public SpellChecker() {
        DICTIONARY = new HashSet<String>();
    }

    public void readFile() throws FileNotFoundException {
        try (Scanner fileIn = new Scanner(new File(CORRECTLY_SPELLED_WORDS_FILE_PATH))) {
            while (fileIn.hasNext()) {
                String word = fileIn.next();
                addWordToDictionary(word);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The file with the provided path wasn't found");
        }
    }

    private void addWordToDictionary(String word) {
        DICTIONARY.add(word.toLowerCase());
    }

    public long getDictionarySize() {
        return DICTIONARY.size();
    }

    public boolean isCorrectlySpelled(String wordToCheck) {
        return DICTIONARY.contains(wordToCheck.toLowerCase());
    }

    public Set<String> getPossibleCorrectSpellings(String wronglySpelledWord) {
        return new CustomTreeSet<String>()
                .addSet(getSimilarWordsByDeletion(wronglySpelledWord))
                .addSet(getSimilarWordsByReplacement(wronglySpelledWord))
                .addSet(getSimilarWordsByInsertion(wronglySpelledWord))
                .addSet(getSimilarWordsByInterchangingConsecutiveCharacters(wronglySpelledWord))
                .addSet(getSimilarWordsByGeneratingTwoWords(wronglySpelledWord));
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

    private Set<String> getSimilarWordsByReplacement(String word) {
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

    private Set<String> getSimilarWordsByInsertion(String word) {
        Set<String> similarWords = new TreeSet<String>();
        CharIterator charIterator;
        for (int i = 0; i < word.length(); i++) {
            charIterator = new CharIterator();
            while (charIterator.hasNext()) {
                String nextChar = charIterator.next();
                if (i == word.length() - 1 && isCorrectlySpelled(word + nextChar)) {
                    similarWords.add(word + nextChar);
                    continue;
                }

                String wordWithReplacedCharacter = word.substring(0, i) + nextChar + word.substring(i);
                if (isCorrectlySpelled(wordWithReplacedCharacter)) {
                    similarWords.add(wordWithReplacedCharacter);
                }
            }
        }
        return similarWords;
    }

    private Set<String> getSimilarWordsByInterchangingConsecutiveCharacters(String word) {
        Set<String> similarWords = new TreeSet<String>();
        for (int i = 0; i < word.length() - 1; i++) {
            char[] chars = word.toCharArray();
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
            String newWord = new String(chars);

            if (isCorrectlySpelled(newWord)) {
                similarWords.add(newWord);
            }
        }
        return similarWords;
    }

    private Set<String> getSimilarWordsByGeneratingTwoWords(String word) {
        Set<String> similarWords = new TreeSet<String>();
        for (int i = 0; i < word.length(); i++) {
            final String word1 = word.substring(0, i);
            final String word2 = word.substring(i);
            if (isCorrectlySpelled(word1) && isCorrectlySpelled(word2)) {
                similarWords.add(word1 + " " + word2);
            }
        }
        return similarWords;
    }
}
