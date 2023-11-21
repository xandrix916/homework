package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {

    @BeforeEach
    void setUp() {
        problem8 = new Problem8();
    }

    private Problem8 problem8;

    @Test
    void checkFirst() {
        // arrange
        String toCheck = "01010";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckFirst() {
        // arrange
        String toCheck = "01010010101";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);

        // assert
        assertTrue(response);
    }

    @Test
    void fallFirst() {
        // arrange
        String toCheck = "010100101011";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallFirst() {
        // arrange
        String toCheck = "12345";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);

        // assert
        assertFalse(response);
    }

    @Test
    void checkSecond() {
        // arrange
        String toCheck = "01010";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckSecond() {
        // arrange
        String toCheck = "110100101011";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);

        // assert
        assertTrue(response);
    }

    @Test
    void fallSecond() {
        // arrange
        String toCheck = "010100101011";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallSecond() {
        // arrange
        String toCheck = "01015";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);

        // assert
        assertFalse(response);
    }

    @Test
    void checkThird() {
        // arrange
        String toCheck = "01010";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckThird() {
        // arrange
        String toCheck = "01010010101";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);

        // assert
        assertTrue(response);
    }

    @Test
    void fallThird() {
        // arrange
        String toCheck = "01010010101100";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallThird() {
        // arrange
        String toCheck = "10203045";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);

        // assert
        assertFalse(response);
    }

    @Test
    void checkFourth() {
        // arrange
        String toCheck = "1101010001";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckFourth() {
        // arrange
        String toCheck = "11100011010";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);

        // assert
        assertTrue(response);
    }

    @Test
    void oneMoreCheckFourth() {
        // arrange
        String toCheck = "1";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);

        // assert
        assertTrue(response);
    }

    @Test
    void fallFourth() {
        // arrange
        String toCheck = "11";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallFourth() {
        // arrange
        String toCheck = "111";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);

        // assert
        assertFalse(response);
    }

    @Test
    void oneMoreFallFourth() {
        // arrange
        String toCheck = "12345";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);

        // assert
        assertFalse(response);
    }

    @Test
    void checkFifth() {
        // arrange
        String toCheck = "1010111010";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckFifth() {
        // arrange
        String toCheck = "10101111111110101";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);

        // assert
        assertTrue(response);
    }

    @Test
    void fallFifth() {
        // arrange
        String toCheck = "10100101010";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallFifth() {
        // arrange
        String toCheck = "12121";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);

        // assert
        assertFalse(response);
    }

    @Test
    void checkSixth() {
        // arrange
        String toCheck = "0000000";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherCheckSixth() {
        // arrange
        String toCheck = "001000000000000000000000";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);

        // assert
        assertTrue(response);
    }

    @Test
    void fallSixth() {
        // arrange
        String toCheck = "010100101011";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);

        // assert
        assertFalse(response);
    }

    @Test
    void anotherFallSixth() {
        // arrange
        String toCheck = "01005";

        // act
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);

        // assert
        assertFalse(response);
    }
}
