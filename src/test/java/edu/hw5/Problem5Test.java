package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem5Test {
    @BeforeEach
    void setUp() {
        problem5 = new Problem5();
    }

    private Problem5 problem5;

    @Test
    void wowTest() {
        // arrange
        String number = "О287ГО150";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertTrue(response);
    }

    @Test
    void oneMoreTest() {
        // arrange
        String number = "Н519ТО154";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertTrue(response);
    }

    @Test
    void exampleTest() {
        // arrange
        String number = "А123ВЕ777";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertTrue(response);
    }

    @Test
    void exampleFall() {
        // arrange
        String number = "123АВЕ777";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherExampleFall() {
        // arrange
        String number = "А123ВГ77";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertFalse(response);
    }

    @Test
    void wrongCountry() {
        // arrange
        String number = "THE CAPN";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertFalse(response);
    }

    @Test
    void betterCallSaul() {
        // arrange
        String number = "LWYRUP";

        // act
        boolean response = problem5.isValidated(number);

        // assert
        assertFalse(response);
    }
}
