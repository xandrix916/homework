package edu.project1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Config {
    private final static Logger LOGGER = LogManager.getLogger();
    private final int totalAttempts;
    private final static int DEFAULT_TOTAL_ATTEMPTS = 5;

    private final ArrayList<String> dictionary;

//    public Config(int attempts, ArrayList<String> dictionary) {
//        this.totalAttempts = attempts;
//        this.dictionary = dictionary;
//    }

    public Config() {
        this.totalAttempts = DEFAULT_TOTAL_ATTEMPTS;
        this.dictionary = new ArrayList<>();
        File file = new File("src/main/java/edu/project1/defaultDictionary.txt");
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                dictionary.add(scanner.nextLine());
            }
        } catch (IOException ioException) {
            LOGGER.info("No such file found.");
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
