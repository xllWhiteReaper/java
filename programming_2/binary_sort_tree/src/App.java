package programming_2.binary_sort_tree.src;

import programming_2.binary_sort_tree.models.BinarySortTree;

public class App {
    public static void main(String[] args) {
        // System.out.println("Works");
        System.out.println("Works 2");

        BinarySortTree<Double> binarySortTree = new BinarySortTree<Double>(Double::compareTo);
        System.out.println("Nothing wrong Huston!");
        binarySortTree.treeInsert(2.345);
        binarySortTree.treeInsert(10.00);
        binarySortTree.treeInsert(1.40);
        binarySortTree.inOrderTraversal();
    }
}
