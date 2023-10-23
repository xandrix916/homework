package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

class Problem3Test {
    @BeforeEach
    void setUp() {
        problem3 = new Problem3();
    }

    private Problem3 problem3;

    @Test
    void doubleB() {
        String response = problem3.freqDict(new ArrayList<>(Arrays.asList("a", "bb", "a", "bb"))).toString();
        Assertions.assertEquals("{bb=2, a=2}", response);
    }

    @Test
    void thisAndThat() {
        String response = problem3.freqDict(new ArrayList<>(Arrays.asList("this", "and", "that", "and"))).toString();
        Assertions.assertEquals("{that=1, and=2, this=1}", response);
    }

    @Test
    void codeAndBug() {
        String response = problem3.freqDict(new ArrayList<>(Arrays.asList("код", "код", "код", "bug"))).toString();
        Assertions.assertEquals("{код=3, bug=1}", response);
    }

    @Test
    void doubleOneDoubleTwo() {
        String response = problem3.freqDict(new ArrayList<>(Arrays.asList(1, 1, 2, 2))).toString();
        Assertions.assertEquals("{1=2, 2=2}", response);
    }

    @Test
    void favouriteMathConstants() {
        String response = problem3.freqDict(new ArrayList<>(Arrays.asList(Math.PI, Math.E, Problem3.PHI, Math.E, Math.PI))).toString();
        Assertions.assertEquals("{1.618033988749895=1, 2.718281828459045=2, 3.141592653589793=2}", response);
    }

    @Test
    void nothingToTest() {
        String response = problem3.freqDict(new ArrayList<>()).toString();
        Assertions.assertEquals("{}", response);
    }
}
