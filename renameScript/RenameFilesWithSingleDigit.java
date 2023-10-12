///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class RenameFilesWithSingleDigit {

    public static void main(String[] args) {

        Path startDir = Paths.get(args[0]);

        try {
            Files.walk(startDir, Integer.MAX_VALUE)
                    .filter(Files::isRegularFile) 
                    .filter(RenameFilesWithSingleDigit::shouldRename)
                    .forEach(RenameFilesWithSingleDigit::renameFile); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean shouldRename(Path filePath) {
        return filePath.getFileName().toString().matches("^\\d\\..*");
    }

    private static void renameFile(Path filePath) {
        Path newFileName = filePath.resolveSibling("0" + filePath.getFileName().toString());

        try {
            Files.move(filePath, newFileName);
            System.out.println("Renamed: " + filePath + " to " + newFileName);
        } catch (IOException e) {
            System.err.println("Failed to rename: " + filePath);
            e.printStackTrace();
        }
    }
}