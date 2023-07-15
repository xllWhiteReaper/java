package programming_2.spell_checker.utils;

public class CharIterator {
    private final String characters = "abcdefghiklmnopqrstuvwxyz";
    private int currentIndex;

    public CharIterator() {
        currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < characters.length();
    }

    public String next() {
        if (hasNext()) {
            final String currentChar = characters.substring(currentIndex, currentIndex + 1);
            currentIndex++;
            return currentChar;
        }

        return "";
    }
}
