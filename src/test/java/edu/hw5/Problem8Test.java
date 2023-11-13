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
        String toCheck = "01010";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);
        assertTrue(response);
    }

    @Test
    void anotherCheckFirst() {
        String toCheck = "01010010101";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);
        assertTrue(response);
    }

    @Test
    void fallFirst() {
        String toCheck = "010100101011";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);
        assertFalse(response);
    }

    @Test
    void anotherFallFirst() {
        String toCheck = "12345";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ODD_LENGTH_REGEX);
        assertFalse(response);
    }

    @Test
    void checkSecond() {
        String toCheck = "01010";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);
        assertTrue(response);
    }

    @Test
    void anotherCheckSecond() {
        String toCheck = "110100101011";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);
        assertTrue(response);
    }

    @Test
    void fallSecond() {
        String toCheck = "010100101011";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);
        assertFalse(response);
    }

    @Test
    void anotherFallSecond() {
        String toCheck = "01015";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.TWO_OPTIONS);
        assertFalse(response);
    }

    @Test
    void checkThird() {
        String toCheck = "01010";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);
        assertTrue(response);
    }

    @Test
    void anotherCheckThird() {
        String toCheck = "01010010101";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);
        assertTrue(response);
    }

    @Test
    void fallThird() {
        String toCheck = "01010010101100";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);
        assertFalse(response);
    }

    @Test
    void anotherFallThird() {
        String toCheck = "10203045";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MOD_THREE_ZERO);
        assertFalse(response);
    }

    @Test
    void checkFourth() {
        String toCheck = "1101010001";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);
        assertTrue(response);
    }

    @Test
    void anotherCheckFourth() {
        String toCheck = "11100011010";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);
        assertTrue(response);
    }

    @Test
    void oneMoreCheckFourth() {
        String toCheck = "1";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);
        assertTrue(response);
    }

    @Test
    void fallFourth() {
        String toCheck = "11";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);
        assertFalse(response);
    }

    @Test
    void anotherFallFourth() {
        String toCheck = "111";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);
        assertFalse(response);
    }

    @Test
    void oneMoreFallFourth() {
        String toCheck = "12345";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_EXCEPT_ELEVENS);
        assertFalse(response);
    }

    @Test
    void checkFifth() {
        String toCheck = "1010111010";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);
        assertTrue(response);
    }

    @Test
    void anotherCheckFifth() {
        String toCheck = "10101111111110101";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);
        assertTrue(response);
    }

    @Test
    void fallFifth() {
        String toCheck = "10100101010";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);
        assertFalse(response);
    }

    @Test
    void anotherFallFifth() {
        String toCheck = "12121";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.ANY_ODD_SYMBOL_IS_ONE);
        assertFalse(response);
    }

    @Test
    void checkSixth() {
        String toCheck = "0000000";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);
        assertTrue(response);
    }

    @Test
    void anotherCheckSixth() {
        String toCheck = "001000000000000000000000";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);
        assertTrue(response);
    }

    @Test
    void fallSixth() {
        String toCheck = "010100101011";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);
        assertFalse(response);
    }

    @Test
    void anotherFallSixth() {
        String toCheck = "01005";
        boolean response = problem8.checkString(toCheck, Problem8.RegexCase.MORE_TWO_ZEROS_NO_MORE_ONE_ONE);
        assertFalse(response);
    }
}
