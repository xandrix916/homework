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
        String number = "О287ГО150";
        boolean response = problem5.isValidated(number);
        assertTrue(response);
    }

    @Test
    void oneMoreTest() {
        String number = "Н519ТО154";
        boolean response = problem5.isValidated(number);
        assertTrue(response);
    }

    @Test
    void exampleTest() {
        String number = "А123ВЕ777";
        boolean response = problem5.isValidated(number);
        assertTrue(response);
    }

    @Test
    void exampleFall() {
        String number = "123АВЕ777";
        boolean response = problem5.isValidated(number);
        assertFalse(response);
    }

    @Test
    void anotherExampleFall() {
        String number = "А123ВГ77";
        boolean response = problem5.isValidated(number);
        assertFalse(response);
    }

    @Test
    void wrongCountry() {
        String number = "THE CAPN";
        boolean response = problem5.isValidated(number);
        assertFalse(response);
    }

    @Test
    void betterCallSaul() {
        String number = "LWYRUP";
        boolean response = problem5.isValidated(number);
        assertFalse(response);
    }
}
