package programming_2.binary_sort_tree.models;

import java.util.Comparator;

public class BinarySortTree<T> {

    private Long size = 0L;
    private TreeNode<T> root;
    private final Comparator<T> COMPARATOR;
    private Long maximumTreeLeafDepth = -1L;
    private Long leavesDepthSum = 0L;
    private Long numberOfLeaves = 0L;

    public BinarySortTree(Comparator<T> comparator) {
        this.COMPARATOR = comparator;
    }

    /**
     * Add the item to the binary sort tree to which the global variable
     * "root" refers. (Note that root can't be passed as a parameter to
     * this routine because the value of root might change, and a change
     * in the value of a formal parameter does not change the actual parameter.)
     */
    public void treeInsert(T newItem) {
        if (root == null) {
            // The tree is empty. Set root to point to a new node containing
            // the new item. This becomes the only node in the tree.
            root = new TreeNode<T>(newItem);
            size += 1;
            // System.out.println("Inserted node " + newItem + " at root");
            return;
        }
        TreeNode<T> runner; // Runs down the tree to find a place for newItem.
        runner = root; // Start at the root.
        while (true) {
            int comparison = COMPARATOR.compare(newItem, runner.item);
            if (comparison < 0) {
                // Since the new item is less than the item in runner,
                // it belongs in the left subtree of runner. If there
                // is an open space at runner.left, add a new node there.
                // Otherwise, advance runner down one level to the left.
                if (runner.left == null) {
                    runner.left = new TreeNode<T>(newItem);
                    size += 1;
                    // System.out.println("Inserted node " + newItem + " at the left of " +
                    // runner.item);
                    return; // New item has been added to the tree.
                } else
                    runner = runner.left;
            } else {
                // Since the new item is greater than or equal to the item in
                // runner it belongs in the right subtree of runner. If there
                // is an open space at runner.right, add a new node there.
                // Otherwise, advance runner down one level to the right.
                if (runner.right == null) {
                    runner.right = new TreeNode<T>(newItem);
                    size += 1;
                    // System.out.println("Inserted node " + newItem + " at the right of " +
                    // runner.item);
                    return; // New item has been added to the tree.
                } else
                    runner = runner.right;
            }
        } // end while
    } // end treeInsert()

    /**
     * Return true if item is one of the items in the binary
     * sort tree to which root points. Return false if not.
     */
    public boolean treeContains(TreeNode<T> root, T item) {
        if (root == null) {
            // Tree is empty, so it certainly doesn't contain item.
            return false;
        } else if (item.equals(root.item)) {
            // Yes, the item has been found in the root node.
            return true;
        } else if (COMPARATOR.compare(item, root.item) < 0) {
            // If the item occurs, it must be in the left subtree.
            return treeContains(root.left, item);
        } else {
            // If the item occurs, it must be in the right subtree.
            return treeContains(root.right, item);
        }
    } // end treeContains()

    public void inOrderTraversal() {
        treeList(root);
    }

    public Long size() {
        return this.size;
    }

    public void getSumOfAllLeavesDepthsAndHighestDepth() {
        leavesDepthSum = 0L;
        maximumTreeLeafDepth = -1L;
        numberOfLeaves = 0L;
        addLeaveDepthAndFindHighestDepth(root, 0);
        System.out.println();
        System.out.println();
        System.out.println("The sum of the depths for all the leaves is: " + leavesDepthSum);
        System.out.println("The number of leaves is: " + numberOfLeaves);
        final double averageLeaveDepth = (double) leavesDepthSum / numberOfLeaves;
        System.out.printf("The average depth for all the leaves is:  %.3f%n", averageLeaveDepth);
        System.out.println("The maximum leave depth is: " + maximumTreeLeafDepth);
    }

    private void addLeaveDepthAndFindHighestDepth(TreeNode<T> node, int depth) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (depth > maximumTreeLeafDepth) {
                maximumTreeLeafDepth = Long.valueOf(depth);
            }
            leavesDepthSum += depth;
            numberOfLeaves += 1;
        }
        addLeaveDepthAndFindHighestDepth(node.left, depth + 1);
        addLeaveDepthAndFindHighestDepth(node.right, depth + 1);
    }

    /**
     * Print the items in the tree in inorder, one item to a line.
     * Since the tree is a sort tree, the output will be in increasing order.
     */
    private void treeList(TreeNode<T> node) {
        if (node != null) {
            treeList(node.left); // Print items in left subtree.
            System.out.println("  " + node.item); // Print item in the node.
            treeList(node.right); // Print items in the right subtree.
        }
    } // end treeList()

    /**
     * Count the nodes in the binary tree.
     * 
     * @param node A pointer to the root of the tree. A null value indicates
     *             an empty tree.
     * @return the number of nodes in the tree to which node points. For an
     *         empty tree, the value is zero.
     */
    private static int countNodes(TreeNode<String> node) {
        if (node == null) {
            // Tree is empty, so it contains no nodes.
            return 0;
        } else {
            // Add up the root node and the nodes in its two subtrees.
            int leftCount = countNodes(node.left);
            int rightCount = countNodes(node.right);
            return 1 + leftCount + rightCount;
        }
    } // end countNodes()

}
// end class SortTreeDemo