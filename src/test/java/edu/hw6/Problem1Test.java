package edu.hw6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {
    @BeforeEach
    void setUp() {
        problem1 = new Problem1();
        restoreOriginals();
    }

    @SuppressWarnings("all")
    private void restoreOriginals() {
        DiskMap diskMap = new DiskMap(new HashMap<>(){{
            put("Google", "Larry Page");
            put("Apple", "Steve Jobs");
            put("Valve", "Gabe Newell");
            put("Microsoft", "Bill Gates");
            put("Tesla", "Elon Musk");
            put("Facebook", "Mark Zuckerberg");
        }}, Path.of("src/main/java/edu/hw6/toread1.txt"));
        diskMap = new DiskMap(new HashMap<>());
    }

    private Problem1 problem1;

    @Test
    void famousPeople() {
        // arrange
        Path path = Path.of("src/main/java/edu/hw6/toread1.txt");


        // act
        DiskMap diskMap = problem1.createFromFileAndWrite(path, new HashMap<>(){{put("Disney", "Walt Disney");}},
            List.of("Tesla", "Facebook"));

        // assert
        assertEquals(new HashMap<>(){{
            put("Google", "Larry Page");
            put("Apple", "Steve Jobs");
            put("Valve", "Gabe Newell");
            put("Microsoft", "Bill Gates");
            put("Disney", "Walt Disney");
        }}, diskMap.getBufferMap());

    }

    @Test
    void famousPeople2() {
        // arrange
        HashMap<String, String> initValues = new HashMap<>(){{
            put("Google", "Larry Page");
            put("Apple", "Steve Jobs");
            put("Valve", "Gabe Newell");
            put("Microsoft", "Bill Gates");
            put("Tesla", "Elon Musk");
            put("Facebook", "Mark Zuckerberg");
        }};

        // act
        DiskMap diskMap = problem1.createToFileAndWrite(initValues, new HashMap<>(){{put("Disney", "Walt Disney");}},
            List.of("Tesla", "Facebook"));

        // assert
        assertEquals(new HashMap<>(){{
            put("Google", "Larry Page");
            put("Apple", "Steve Jobs");
            put("Valve", "Gabe Newell");
            put("Microsoft", "Bill Gates");
            put("Disney", "Walt Disney");
        }}, diskMap.getBufferMap());
    }

    @Test
    void famousPeopleAlternativeTest() {
        // arrange
        HashMap<String, String> initValues = new HashMap<>(){{
            put("Google", "Larry Page");
            put("Apple", "Steve Jobs");
            put("Valve", "Gabe Newell");
            put("Microsoft", "Bill Gates");
            put("Tesla", "Elon Musk");
            put("Facebook", "Mark Zuckerberg");
        }};
        Path path = Path.of("src/main/java/edu/hw6/toread1.txt");

        // act
        DiskMap diskMap1 = problem1.createFromFileAndWrite(path, new HashMap<>(){{put("Disney", "Walt Disney");}},
            List.of("Tesla", "Facebook"));
        DiskMap diskMap2 = problem1.createToFileAndWrite(initValues, new HashMap<>(){{put("Disney", "Walt Disney");}},
            List.of("Tesla", "Facebook"));

        // assert
        assertEquals(diskMap1.getBufferMap(), diskMap2.getBufferMap());
    }
}
