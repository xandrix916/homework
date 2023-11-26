package edu.hw7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {
    @BeforeEach
    void setUp() {
        problem1 = new Problem1();
    }

    private Problem1 problem1;

    @Test
    void likeInExample() {
        // arrange
        int countLimit = 100_000;

        // act
        int response = problem1.count(countLimit, countLimit);

        // assert
        assertEquals(200_000, response);
    }

    @Test
    void randomNumbers() {
        // arrange
        int countLimit1 = (int) (Math.random() * 10_000) + 1;
        int countLimit2 = (int) (Math.random() * 10_000) + 1;

        // act
        int response = problem1.count(countLimit1, countLimit2);

        // assert
        assertEquals(countLimit1 + countLimit2, response);
    }

}
