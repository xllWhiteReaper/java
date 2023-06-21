# IO Assignment

This is a Java program that demonstrates how to read a resource from the internet using a URL, and save it to a file using input/output (IO) operations. The program takes a user-provided URL and file name as input, and saves the resource to the specified file name. Currently, the program only supports saving text files (`.txt`), but it can be easily modified to support other file types as well.

## Usage

To run the program, simply compile and run the `Main.java` file. The program will prompt you to enter a URL and a file name for the output file. Once you provide the inputs, the program will download the resource from the URL and save it to the specified file name.

Example usage:

```
$ javac Main.java
$ java Main
Enter URL: https://www.example.com/sample.txt
Enter file name: output.txt
Resource saved to output.txt
```

Note that the program assumes that the resource at the specified URL is a text file. If the resource is in a different format, the program may not work as expected.

## Dependencies

The program uses the following Java classes and libraries:

- `java.net.URL`: to represent the URL of the resource to be downloaded
- `java.io.InputStream`: to read the input stream from the URL
- `java.io.FileOutputStream`: to write the output stream to a file

## License

This program is released under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0). Feel free to use, modify, and distribute the code as you see fit.
