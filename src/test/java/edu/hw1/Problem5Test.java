package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem5Test {
    @BeforeEach
    void setUp() {
        problem5 = new Problem5();
    }

    private Problem5 problem5;

    @Test
    void goodPasswordFourPlusBackwards() {
        int number = 12344321;
        boolean response = problem5.isPalindromeDescendant(number);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void firstTestWithDescendant() {
        int number = 11211230;
        boolean response = problem5.isPalindromeDescendant(number);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void secondTestWithDescendant() {
        int number = 13001120;
        boolean response = problem5.isPalindromeDescendant(number);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void elevenAngryMen() {
        int number = 11;
        boolean response = problem5.isPalindromeDescendant(number);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void obviouslyFail() {
        int number = 135;
        boolean response = problem5.isPalindromeDescendant(number);
        Assertions.assertThat(response).isEqualTo(false);
    }
}
