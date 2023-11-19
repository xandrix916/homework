package edu.hw6;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Problem2Test {
    private static final String linkOrigin = "src/main/java/edu/hw6/Tinkoff Bank Biggest Secret.txt";
    private static final String linkCopy = "src/main/java/edu/hw6/Tinkoff Bank Biggest Secret — копия.txt";
    private static final String topGunOrigin = "src/main/java/edu/hw6/topgun.txt";
    private static final String topGunCopy = "src/main/java/edu/hw6/topgun — копия.txt";
    private static final String topGunSecondCopy = "src/main/java/edu/hw6/topgun — копия (2).txt";

    private static final String topGunCopyCopy = "src/main/java/edu/hw6/topgun — копия — копия.txt";

    @BeforeEach
    void setUp() {
        restoreOriginals();
        problem2 = new Problem2();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void restoreOriginals() {
        File file = new File(linkCopy);
        file.delete();
        file = new File(topGunSecondCopy);
        file.delete();
        file = new File(topGunCopyCopy);
        file.delete();
    }

    private Problem2 problem2;

    @Test
    void likeInExample() {
        // arrange

        Path path = Path.of(linkOrigin);

        // act
        boolean success = problem2.cloneFile(path);

        // assert

        try {
            assertEquals(DiskMap.readFile(path), DiskMap.readFile(Path.of(linkCopy)));
        } catch (IOException e) {
            log.error("Assertion trouble");
            success = false;
        }
        assertTrue(success);
    }

    @Test
    void anotherOne() {
        // arrange
        Path path = Path.of(topGunOrigin);

        // act
        boolean success = problem2.cloneFile(path);

        // assert

        try {
            assertEquals(DiskMap.readFile(path), DiskMap.readFile(Path.of(topGunSecondCopy)));
        } catch (IOException e) {
            log.error("Assertions trouble");
            success = false;
        }
        assertTrue(success);
    }

    @Test
    void copyOfCopy() {
        // arrange
        Path path = Path.of(topGunCopy);

        // act
        boolean success = problem2.cloneFile(path);

        // assert

        try {
            assertEquals(DiskMap.readFile(path), DiskMap.readFile(Path.of(topGunCopyCopy)));
        } catch (IOException e) {
            log.error("Assertions problem");
            success = false;
        }
        assertTrue(success);
    }
}
