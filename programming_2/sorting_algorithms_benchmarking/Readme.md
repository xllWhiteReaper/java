# Sorting Algorithm Performance Comparison

This Java program compares the performance of two sorting algorithms: insertion sort and Java's built-in sorting algorithm (using the `Arrays.sort()` method). The program generates an array of random integers with a size that can be customized by changing the `ARRAY_SIZE` constant. The program then sorts the array using both algorithms and measures the time it takes to sort the array.

## Usage

To run the program, simply compile and run the `App.java` file. The program will output the time it takes to sort the array using both algorithms. You can customize the size of the array by changing the `ARRAY_SIZE` constant in the code.

Example usage:

```
$ javac App.java
$ java App
Task Insertion Sort took 20643 milliseconds.
Task Java Built-in Sorting took 20643 milliseconds.
```

Note that the actual time it takes to sort the array may vary depending on the speed of your computer and the size of the array.

## Customization

You can customize the size of the array that the program sorts by changing the `ARRAY_SIZE` constant in the code.

## Additional Classes

This program uses three additional classes to help with the sorting and timing:

- `TimeManager`: A helper class used to manage timing operations.
- `NumberGenerator`: A helper class used to generate random integer arrays.
- `ArraySorter`: A helper class that includes the insertion sort algorithm.

## License

This program is released under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0). See the `LICENSE` file for more information. Feel free to use, modify, and distribute the code as you see fit.

## Project Repository

The project repository can be found at: https://github.com/yourusername/sorting-performance-comparison
