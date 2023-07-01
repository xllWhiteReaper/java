package programming_2.binary_sort_tree.models;

/**
 * An object of type TreeNode represents one node in a binary tree of T.
 */
public class TreeNode<T> {
    T item; // The data in this node.
    TreeNode<T> left; // Pointer to left subtree.
    TreeNode<T> right; // Pointer to right subtree.

    TreeNode(T item) {
        // Constructor. Make a node containing the specified string.
        // Note that left and right pointers are initially null.
        this.item = item;
    }
} // end nested class TreeNode