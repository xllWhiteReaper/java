import programming_2.sentences_generator.utils.GrammaticalCategories;
import programming_2.sentences_generator.utils.StringHandler;

/**
 * This app's purpose is to generate sentences given certain rules of english
 * grammar.
 * 
 * The rules are the following. The "|" symbol means an election or the "or"
 * operand. The "[]" symbol means that it is optional.
 * 
 * The syntax rules are:
 * <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
 * 
 * <simple_sentence> ::= <noun_phrase> <verb_phrase>
 * 
 * <noun_phrase> ::= <proper_noun> |
 * <determiner> [ <adjective> ]. <common_noun> [ who <verb_phrase> ]
 * 
 * <verb_phrase> ::= <intransitive_verb> |
 * <transitive_verb> <noun_phrase> |
 * is <adjective> |
 * believes that <simple_sentence>
 * 
 * <conjunction> ::= and | or | but | because
 * 
 * <proper_noun> ::= Fred | Jane | Richard Nixon | Miss America
 * 
 * <common_noun> ::= man | woman | fish | elephant | unicorn
 * 
 * <determiner> ::= a | the | every | some
 * 
 * <adjective> ::= big | tiny | pretty | bald
 * 
 * <intransitive_verb> ::= runs | jumps | talks | sleeps
 * 
 * <transitive_verb> ::= loves | hates | sees | knows | looks for | finds
 */
public class App {
    private static final int SENTENCES_COMPONENTS_LIMIT = 15;
    private static final int SIMPLE_SENTENCES_LENGTH_LIMIT = 100;
    private static final int NUMBER_OF_SENTENCES = 5;

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        for (int i = 0; i < NUMBER_OF_SENTENCES; i++) {
            System.out.println(getSentence(0));
            System.out.println();
        }
    }

    private static String getCategory(String categoryType) {
        return GrammaticalCategories.getCategoryByName(categoryType);
    }

    private static String getSentence(int sentenceCounter) {
        if (sentenceCounter > SENTENCES_COMPONENTS_LIMIT) {
            // termination clause
            return "";
        }

        final String conjunctionAndSentence = getRandomBoolean()
                ? " " + getCategory("conjunction") + " " + getSentence(sentenceCounter + 1)
                : "";
        return getSimpleSentence(0) + conjunctionAndSentence;
    }

    private static String getSimpleSentence(int simpleSentenceComponentsCounter) {
        return simpleSentenceComponentsCounter > SIMPLE_SENTENCES_LENGTH_LIMIT ? ""
                : getNounPhrase(simpleSentenceComponentsCounter + 1) + " "
                        + getVerbPhrase(simpleSentenceComponentsCounter + 2);
    }

    private static String getNounPhrase(int simpleSentenceComponentsCounter) {
        if (simpleSentenceComponentsCounter > SIMPLE_SENTENCES_LENGTH_LIMIT) {
            // termination clause
            return "";
        }

        int possibilities = 2;
        double probabilityDivision = (double) 1 / possibilities;
        double probability = getRandomDouble();

        if (probability <= probabilityDivision) {
            return getCategory("properNoun");
        } else {
            // In the instructions, a dot (".") appears as the second option for a
            // noun_phrase, maybe it doesn't belong there, but as the instructions indicate,
            // I will leave it there
            String adjective = (getRandomBoolean() ? " " + getCategory("adjective") : "") + ". ";
            String verbPhrase = getRandomBoolean() ? " who " + getVerbPhrase(simpleSentenceComponentsCounter + 1) : "";
            return getCategory("determiner") + adjective + StringHandler
                    .toTitleCase(getCategory("commonNoun")) + verbPhrase;
        }
    }

    private static String getVerbPhrase(int simpleSentenceComponentsCounter) {
        if (simpleSentenceComponentsCounter > SIMPLE_SENTENCES_LENGTH_LIMIT) {
            // termination clause
            return "";
        }

        int possibilities = 4;
        double probabilityDivision = (double) 1 / possibilities;
        double probability = getRandomDouble();

        if (probability <= probabilityDivision) {
            return getCategory("intransitiveVerb");
        } else if (probability <= 2 * probabilityDivision) {
            return getCategory("transitiveVerb") + " " + getNounPhrase(simpleSentenceComponentsCounter + 1);
        } else if (probability <= 3 * probabilityDivision) {
            return "is " + getCategory("adjective");
        } else {
            return "believes that " + getSimpleSentence(simpleSentenceComponentsCounter + 1);
        }
    }

    private static double getRandomDouble() {
        return Math.random();
    }

    private static boolean getRandomBoolean() {
        return getRandomDouble() > 0.5 ? true : false;
    }
}