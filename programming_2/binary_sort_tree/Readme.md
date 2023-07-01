# Binary Sort Tree

Binary Sort Tree - Average Leaf Depth vs Maximum Depth

## Description

This project is a Java implementation of a binary sort tree with random insertion, which does not auto-balance. The goal of this project is to calculate the average depth of the leaves of the tree and compare it to the maximum depth of the tree.

The BinarySortTree class implements a generic binary sort tree with methods to insert a new node, traverse the tree in different orders, and calculate the average leaf depth and maximum depth of the tree. The TreeNode class represents a node in the tree.

## Getting Started

### Dependencies

- Java 8 or higher

### Installing

1. Clone the project from GitHub: `git clone https://github.com/xllWhiteReaper/java/tree/develop`
2. Navigate to the following folder: `programming_2/binary_sort_tree`
3. Open the project in your IDE (Integrated Development Environment) of choice
4. Switch branches by using the following command: `git checkout develop`
5. Run the `App` class to start the application

### Executing program

To run the program, execute the main method in the App class. This will create a new instance of the BinarySortTree class, insert 1023 random nodes, and calculate the average leaf depth and maximum depth of the tree. The results will be printed to the console.

```
final BinarySortTree<Double> binarySortTree = new BinarySortTree<Double>(Double::compareTo);
fillBinarySortTree(binarySortTree);
binarySortTree.getSumOfAllLeavesDepthsAndHighestDepth();

```

## Help

If you have any problems or suggestions for this project, please feel free to open an issue or submit a pull request.

## License

This program is released under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).

## Acknowledgments

- University of the People (for providing the initial code for the String BST)
