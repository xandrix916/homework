package edu.project1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Config {
    private final int totalAttempts;
    private final ArrayList<String> dictionary;

//    public Config(int attempts, ArrayList<String> dictionary) {
//        this.totalAttempts = attempts;
//        this.dictionary = dictionary;
//    }

    public Config() {
        this.totalAttempts = 5;
        this.dictionary = new ArrayList<>();
        File file = new File("src/main/java/edu/project1/defaultDictionary.txt");
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                dictionary.add(scanner.nextLine());
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public String getRandomWord() {
        int randomIndex = (int) (Math.random() * dictionary.size());
        return dictionary.get(randomIndex);
    }
}
