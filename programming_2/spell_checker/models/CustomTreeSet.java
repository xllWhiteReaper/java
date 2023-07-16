package programming_2.spell_checker.models;

import java.util.Set;
import java.util.TreeSet;

public class CustomTreeSet<E> extends TreeSet<E> {
    public CustomTreeSet<E> addSet(Set<E> setToAdd) {
        this.addAll(setToAdd);
        return this;
    }

    @SafeVarargs
    public final CustomTreeSet<E> addSets(Set<E>... setsToAdd) {
        for (Set<E> setToAdd : setsToAdd) {
            this.addAll(setToAdd);
        }
        return this;
    }
}
