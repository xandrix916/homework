package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem7Test {
    @BeforeEach
    void setUp() {
        problem7 = new Problem7();
    }

    private Problem7 problem7;

    @Test
    void testRightEight() {
        int n = 8, shift = 1;
        int response = problem7.rotateRight(n, shift);
        Assertions.assertThat(response).isEqualTo(4);
    }

    @Test
    void testLeftSixteen() {
        int n = 16, shift = 1;
        int response = problem7.rotateLeft(n, shift);
        Assertions.assertThat(response).isOne();
    }

    @Test
    void testLeftSeventeenShiftTwo() {
        int n = 17, shift = 2;
        int response = problem7.rotateLeft(n, shift);
        Assertions.assertThat(response).isEqualTo(6);
    }

    @Test
    void testLeftTenShiftThree() {
        int n = 10, shift = 3;
        int response = problem7.rotateLeft(n, shift);
        Assertions.assertThat(response).isEqualTo(5);
    }
}
