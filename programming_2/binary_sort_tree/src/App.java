package programming_2.binary_sort_tree.src;

import java.util.Random;

import programming_2.binary_sort_tree.models.BinarySortTree;

public class App {
    private static final int NUMBER_OF_NODES = 1023;
    private static final double DOUBLE_BOUND = 3000;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        final BinarySortTree<Double> binarySortTree = new BinarySortTree<Double>(Double::compareTo);
        fillBinarySortTree(binarySortTree);
        binarySortTree.getSumOfAllLeavesDepthsAndHighestDepth();
    }

    private static double getRandomDouble() {
        return RANDOM.nextDouble(0, DOUBLE_BOUND);
    }

    private static void fillBinarySortTree(BinarySortTree<Double> binarySortTree) {
        for (int i = 0; i < NUMBER_OF_NODES; i++) {
            binarySortTree.treeInsert(getRandomDouble());
        }
    }
}
