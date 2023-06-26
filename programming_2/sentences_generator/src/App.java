import programming_2.sentences_generator.utils.GrammaticalCategories;
import programming_2.sentences_generator.utils.StringHandler;

public class App {
    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        for (int i = 0; i < 4; i++) {
            System.out.println(getNounPhrase());
        }
    }

    private static String getCategory(String categoryType) {
        return GrammaticalCategories.getCategoryByName(categoryType);
    }

    private static String getNounPhrase() {
        int possibilities = 2;
        double probabilityDivision = (double) 1 / possibilities;
        double probability = getRandomDouble();

        if (probability <= probabilityDivision) {
            return getCategory("properNoun");
        } else {
            String adjective = (getRandomDouble() > 0.5 ? " " + getCategory("adjective") : "") + ". ";
            String verbPhrase = getRandomDouble() > 0.5 ? "who " + getVerbPhrase() : "";
            return getCategory("determiner") + adjective + StringHandler
                    .toTitleCase(getCategory("commonNoun")) + " " + verbPhrase;
        }
    }

    private static String getVerbPhrase() {
        int possibilities = 4;
        double probabilityDivision = (double) 1 / possibilities;
        double probability = getRandomDouble();

        if (probability <= probabilityDivision) {
            return getCategory("intransitiveVerb");
        } else if (probability <= 2 * probabilityDivision) {
            return getCategory("transitiveVerb") + " ";
            // + getNounPhrase();
        } else if (probability <= 3 * probabilityDivision) {
            return "is " + getCategory("adjective");
        } else {
            return "believes that ";
            // getSimpleSentence();
        }
    }

    private static double getRandomDouble() {
        return Math.random();
    }
}