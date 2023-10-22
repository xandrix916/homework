package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem4Test {
    @BeforeEach
    void setUp() {
        problem4 = new Problem4();
    }

    private Problem4 problem4;

    @Test
    void teaForTwo() {
        int toConvert = 2;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("II", response);
    }

    @Test
    void twelveAngryMen() {
        int toConvert = 12;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("XII", response);
    }

    @Test
    void twoPowerFour() {
        int toConvert = 16;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("XVI", response);
    }

    @Test
    void answerToMainQuestion() {
        int toConvert = 42;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("XLII", response);
    }

    @Test
    void pulpFiction() {
        int toConvert = 94;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("XCIV", response);
    }

    @Test
    void creativeAssembly() {
        int toConvert = 420;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("CDXX", response);
    }

    @Test
    void literallyIt() {
        int toConvert = 1984;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("MCMLXXXIV", response);
    }

    @Test
    void historicalFail() {
        int toConvert = 4010;
        String response = problem4.convertToRoman(toConvert);
        Assertions.assertEquals("Romans didn't use such numbers", response);
    }
}
