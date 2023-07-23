public class TextHelper {
    public static void printWithIndentation(String text, int indentation) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < indentation; i++) {
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString() + text);
    }
}
