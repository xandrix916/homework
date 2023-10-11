package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem6Test {
    @BeforeEach
    void setUp() {
        problem6 = new Problem6();
    }

    private Problem6 problem6;

    @Test
    void firstTest() {
        int number = 6621;
        int response = problem6.countK(number);
        Assertions.assertThat(response).isEqualTo(5);
    }

    @Test
    void secondTest() {
        int number = 6554;
        int response = problem6.countK(number);
        Assertions.assertThat(response).isEqualTo(4);
    }

    @Test
    void goodPassword() {
        int number = 1234;
        int response = problem6.countK(number);
        Assertions.assertThat(response).isEqualTo(3);
    }

    @Test
    void likeInText() {
        int number = 3524;
        int response = problem6.countK(number);
        Assertions.assertThat(response).isEqualTo(3);
    }

    @Test
    void defaultTest() {
        int number = 6174;
        int response = problem6.countK(number);
        Assertions.assertThat(response).isEqualTo(0);
    }
}
