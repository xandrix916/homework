package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Problem4Test {
    @BeforeEach
    void setUp() {
        problem4 = new Problem4();
    }

    private Problem4 problem4;

    @Test
    void firstTest() {
        String result = problem4.problem4();
        Assertions.assertThat(result).isEqualTo("""
            Class name: edu.hw2.Problem4
            Method name: problem4
            """);
    }

    @Test
    void doubleTest() {
        String result = problem4.doubleProblem4();
        Assertions.assertThat(result).isEqualTo("""
            Class name: edu.hw2.Problem4
            Method name: problem4
            Class name: edu.hw2.Problem4
            Method name: doubleProblem4
            """);
    }

    @Test
    void mainTest() {
        String result = Main.mainFourTest();
        Assertions.assertThat(result).isEqualTo("""
            Class name: edu.hw2.Main
            Method name: mainFourTest
            """);
    }
}
