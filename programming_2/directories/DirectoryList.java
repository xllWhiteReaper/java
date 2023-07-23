import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user, if there are folders, then it lists the files inside that folder as
 * well. The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {

   public static void main(String[] args) {

      String directoryName; // Directory name entered by the user.
      File directory; // File object referring to the directory.
      Scanner scanner; // For reading a line of input from the user.
      scanner = new Scanner(System.in); // scanner reads from standard input.

      System.out.print("Enter a directory name: ");
      directoryName = scanner.nextLine().trim();
      directory = new File(directoryName);

      listFilesDirectoriesAndSubFiles(directory, 0);

      scanner.close();
   } // end main()

   private static void listFilesDirectoriesAndSubFiles(File currentDirectory, int nesting) {
      if (nesting < 0) {
         return;
      }

      if (!currentDirectory.isDirectory()) {
         if (nesting == 0) {
            if (!currentDirectory.exists())
               System.out.println("There is no such directory!");
            else
               System.out.println("That file is not a directory.");
         }
         return;
      }

      String[] currentDirectoryFilesAndDirectories = currentDirectory.list();

      for (String currentDirectoryFileOrSubdirectoryName : currentDirectoryFilesAndDirectories) {
         File possibleDirectory = new File(currentDirectory.getPath() + "/" + currentDirectoryFileOrSubdirectoryName);
         TextHelper.printWithIndentation(currentDirectoryFileOrSubdirectoryName, nesting);
         if (possibleDirectory.isDirectory()) {
            listFilesDirectoriesAndSubFiles(possibleDirectory, nesting + 2);
         }
      }
   }
} // end class DirectoryList
