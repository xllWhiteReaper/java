package programming_2.spell_checker.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import programming_2.spell_checker.models.SpellChecker;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        SpellChecker spellChecker = new SpellChecker();
        System.out.println("Dictionary Size " + spellChecker.getDictionarySize());
    }
}
