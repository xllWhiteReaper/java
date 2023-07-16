package programming_2.spell_checker.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileHandler {
    public static String getFileType(File file) {
        byte[] fileBytes = new byte[10];
        try (FileInputStream in = new FileInputStream(file)) {
            // Read the first few bytes of the file
            in.read(fileBytes);
        } catch (IOException e) {
            // Handle the case where the file could not be read
            return null;
        }

        // Check the file type based on the first few bytes
        if (fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8 && fileBytes[2] == (byte) 0xFF) {
            return "image/jpeg";
        } else if (fileBytes[0] == (byte) 0x89 && fileBytes[1] == (byte) 0x50 && fileBytes[2] == (byte) 0x4E
                && fileBytes[3] == (byte) 0x47) {
            return "image/png";
        } else if (fileBytes[0] == (byte) 0x47 && fileBytes[1] == (byte) 0x49 && fileBytes[2] == (byte) 0x46
                && fileBytes[3] == (byte) 0x38) {
            return "image/gif";
        } else if (fileBytes[0] == (byte) 0x42 && fileBytes[1] == (byte) 0x4D) {
            return "image/bmp";
        } else if (file.getName().toLowerCase().endsWith(".txt")) {
            return "text/plain";
        } else {
            return null;
        }
    }
}