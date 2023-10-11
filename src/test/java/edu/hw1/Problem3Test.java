package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem3Test {
    @BeforeEach
    void setUp(){
        problem3 = new Problem3();
    }

    private Problem3 problem3;

    @Test
    void fourAndTwoNestable() {
        int[] a1 = {1, 2, 3 , 4}, a2 = {0, 6};
        boolean response = problem3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void twoAndTwoNestable() {
        int[] a1 = {3, 1}, a2 = {4, 0};
        boolean response = problem3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void threeAndTwoNotNestable() {
        int[] a1 = {9, 9, 8}, a2 = {8, 9};
        boolean response = problem3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(false);
    }

    @Test
    void fourAndTwoNotNestable() {
        int[] a1 = {1, 2, 3, 4}, a2 = {2, 3};
        boolean response = problem3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(false);
    }

    @Test
    void hollowArray() {
        int[] a1 = {1, 2, 3}, a2 = {};
        boolean response = problem3.isNestable(a1, a2);
        Assertions.assertThat(response).isEqualTo(false);
    }
}
