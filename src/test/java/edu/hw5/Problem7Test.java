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
        // arrange
        String toCheck = "100";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.THIRD_IS_ZERO);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckThirdZero() {
        // arrange
        String toCheck = "110101010101010";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.THIRD_IS_ZERO);

        // assert
        assertTrue(response);
    }

    @Test
    void fallThirdZero() {
        // arrange
        String toCheck = "001";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.THIRD_IS_ZERO);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallThirdZero() {
        // arrange
        String toCheck = "7302323";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.THIRD_IS_ZERO);

        // assert
        assertFalse(response);
    }

    @Test
    void checkFirstLast() {
        // arrange
        String toCheck = "11";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.FIRST_EQUALS_LAST);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckFirstLast() {
        // arrange
        String toCheck = "010101010101010";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.FIRST_EQUALS_LAST);

        // assert
        assertTrue(response);
    }

    @Test
    void fallFirstLast() {
        // arrange
        String toCheck = "001";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.FIRST_EQUALS_LAST);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallFirstLast() {
        // arrange
        String toCheck = "0302320";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.FIRST_EQUALS_LAST);

        // assert
        assertFalse(response);
    }

    @Test
    void checkBetweenOneAndThree() {
        // arrange
        String toCheck = "01";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.BETWEEN_ONE_AND_THREE);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckBetweenOneAndThree() {
        // arrange
        String toCheck = "110";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.BETWEEN_ONE_AND_THREE);

        // assert
        assertTrue(response);
    }

    @Test
    void fallBetweenOneAndThree() {
        // arrange
        String toCheck = "0101010101001";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.BETWEEN_ONE_AND_THREE);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallBetweenOneAndThree() {
        // arrange
        String toCheck = "012";

        // act
        boolean response = problem7.checkString(toCheck, RegexCase.BETWEEN_ONE_AND_THREE);

        // assert
        assertFalse(response);
    }
}
