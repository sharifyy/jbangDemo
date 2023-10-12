///usr/bin/env jbang "$0" "$@" ; exit $?


import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CreateTutorialStructure {

    public static void main(String[] args) {
        // Define the base directory
        String baseDir = "tutorial";

        // Create the base directory
        File tutorialDir = new File(baseDir);
        if (!tutorialDir.exists()) {
            if (tutorialDir.mkdir()) {
                System.out.println("Created directory: " + baseDir);

                // Create chapter directories
                createChapterDirectory(tutorialDir, "chapterOne");
                createChapterDirectory(tutorialDir, "chapterTwo");
                createChapterDirectory(tutorialDir, "chapterThree");

                System.out.println("Directory structure created successfully.");
            } else {
                System.err.println("Failed to create directory: " + baseDir);
            }
        } else {
            System.err.println("Directory already exists: " + baseDir);
        }
    }

    private static void createChapterDirectory(File parentDir, String chapterName) {
        File chapterDir = new File(parentDir, chapterName);
        if (!chapterDir.exists()) {
            if (chapterDir.mkdir()) {
                System.out.println("Created directory: " + chapterName);

                // Create 12 random files in the chapter directory
                createRandomFiles(chapterDir, 12);
            } else {
                System.err.println("Failed to create directory: " + chapterName);
            }
        } else {
            System.err.println("Directory already exists: " + chapterName);
        }
    }

    private static void createRandomFiles(File parentDir, int numFiles) {
        for (int i = 1; i <= numFiles; i++) {
            String fileName = i + "." + getRandomString() + ".mp4";
            File file = new File(parentDir, fileName);

            try {
                if (file.createNewFile()) {
                    System.out.println("Created file: " + fileName);
                } else {
                    System.err.println("Failed to create file: " + fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getRandomString() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
