# Recursive English Sentence Generator

This is a simple Java program that generates English sentences given certain conditions using direct and indirect recursion. The program uses three classes: `App`, `GrammaticalCategories`, and `StringHandler`.

## Installation

To run the program, you need to have Java installed on your computer. You can download Java from the official website: https://www.java.com/en/download/

Once you have Java installed, you can retrieve the program from GitHub by following these steps:

1. Use the following command: `git clone https://github.com/xllWhiteReaper/java/tree/develop`
2. Select the `programming_2/sentences_generator` folder.
3. Switch to the `develop` branch.

Once you have the program downloaded or cloned to your computer, you can compile and run the program using the following commands in your terminal:

```
$ javac App.java
$ java App
```

## Usage

The program generates a specified number of English sentences with varying complexity. The parameters for the number of sentences, the length limit for simple sentences, and the limit for the number of components in a sentence can be changed in the `App` class.

To generate the sentences, the program uses recursion to create complex grammatical structures. The `GrammaticalCategories` class contains methods that return strings representing different grammatical categories, such as noun phrases, verb phrases, and adjectives.

The `StringHandler` class contains a `toTitleCase` method that converts a string to title case by capitalizing the first letter of each word. This method is used to format the generated sentences.

The `App` class contains the `main` method that generates the specified number of sentences by calling the `getSentence` method. `getSentence` decides whether to generate a simple or a complex sentence based on a random boolean value.

For simple sentences, the `getSimpleSentence` method generates a noun phrase and a verb phrase of a specified length using the `App` class, and then combines them into a sentence using a space.

The `getNounPhrase` and `getVerbPhrase` methods can call the `getSimpleSentence` or each other. Therefore, it generates indirect recursion. That's the reason to include certain conditionals to break a possible infinite recursion.

## Parameters

The following parameters can be changed in the `App` class to generate more or larger sentences:

- `NUMBER_OF_SENTENCES`: The number of sentences to generate.
- `SIMPLE_SENTENCES_LENGTH_LIMIT`: The maximum length of components in a simple sentence.
- `SENTENCES_COMPONENTS_LIMIT`: The maximum number of components in a sentence.

## Method Calls Flow

The `method_calls_flow.drawio` file included in the project shows the flow of method calls in the program in order to beer understand possible infinite recursion.

## License

This program is released under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).
