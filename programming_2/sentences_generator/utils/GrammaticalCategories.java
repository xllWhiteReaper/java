package programming_2.sentences_generator.utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GrammaticalCategories {
    private static final Map<String, List<String>> grammaticalCategories = Map.of(
            "conjunction", List.of("and", "or", "but", "because"),
            "properNoun", List.of("Fred", "Jane", "Richard Nixon", "Miss America"),
            "commonNoun", List.of("man", "woman", "fish", "elephant", "unicorn"),
            "determiner", List.of("a", "the", "every", "some"),
            "adjective", List.of("big", "tinny", "pretty", "bald"),
            "intransitiveVerb", List.of("runs", "jumps", "talks", "sleeps"),
            "transitiveVerb", List.of("loves", "hates", "sees", "knows", "looks for", "finds"));
    private static final Random random = new Random();

    public static String getCategoryByName(String categoryName) {
        if (grammaticalCategories.containsKey(categoryName)) {
            List<String> category = grammaticalCategories.get(categoryName);
            return category.get(random.nextInt(category.size()));
        }
        return "";
    }
}
