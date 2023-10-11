package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem2Test {
    @BeforeEach
    void setUp() {
        problem2 = new Problem2();
    }

    private Problem2 problem2;


    @Test
    void numberZero() {
        int number = 0;
        int response = problem2.countDigits(number);
        Assertions.assertThat(response).isEqualTo(1);
    }

    @Test
    void threeDigitNumber() {
        int number = 546;
        int response = problem2.countDigits(number);
        Assertions.assertThat(response).isEqualTo(3);
    }

    @Test
    void eightDigitNumber() {
        int number = 54782341;
        int response = problem2.countDigits(number);
        Assertions.assertThat(response).isEqualTo(8);
    }

    @Test
    void numberBelowZero() {
        int number = -25;
        int response = problem2.countDigits(number);
        Assertions.assertThat(response).isEqualTo(2);
    }
}
