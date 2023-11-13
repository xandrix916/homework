package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem7Test {
    @BeforeEach
    void setUp() {
        problem7 = new Problem7();
    }

    private Problem7 problem7;

    @Test
    void checkThirdZero() {
        String toCheck = "100";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.THIRD_IS_ZERO);
        assertTrue(response);
    }

    @Test
    void anotherCheckThirdZero() {
        String toCheck = "110101010101010";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.THIRD_IS_ZERO);
        assertTrue(response);
    }

    @Test
    void fallThirdZero() {
        String toCheck = "001";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.THIRD_IS_ZERO);
        assertFalse(response);
    }

    @Test
    void anotherFallThirdZero() {
        String toCheck = "7302323";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.THIRD_IS_ZERO);
        assertFalse(response);
    }

    @Test
    void checkFirstLast() {
        String toCheck = "11";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.FIRST_EQUALS_LAST);
        assertTrue(response);
    }

    @Test
    void anotherCheckFirstLast() {
        String toCheck = "010101010101010";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.FIRST_EQUALS_LAST);
        assertTrue(response);
    }

    @Test
    void fallFirstLast() {
        String toCheck = "001";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.FIRST_EQUALS_LAST);
        assertFalse(response);
    }

    @Test
    void anotherFallFirstLast() {
        String toCheck = "0302320";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.FIRST_EQUALS_LAST);
        assertFalse(response);
    }

    @Test
    void checkBetweenOneAndThree() {
        String toCheck = "01";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.BETWEEN_ONE_AND_THREE);
        assertTrue(response);
    }

    @Test
    void anotherCheckBetweenOneAndThree() {
        String toCheck = "110";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.BETWEEN_ONE_AND_THREE);
        assertTrue(response);
    }

    @Test
    void fallBetweenOneAndThree() {
        String toCheck = "0101010101001";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.BETWEEN_ONE_AND_THREE);
        assertFalse(response);
    }

    @Test
    void anotherFallBetweenOneAndThree() {
        String toCheck = "012";
        boolean response = problem7.checkString(toCheck, Problem7.RegexCase.BETWEEN_ONE_AND_THREE);
        assertFalse(response);
    }
}
