package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Problem1Test {
    @Test
    void calculateExample() {
        Problem1 problem1 = new Problem1();
        String result = problem1.calculationExample();
        Assertions.assertThat(result).isEqualTo("Addition[expression1=Exponent[expression=Multiplication" +
            "[expression1=Addition[expression1=Constant[c=2.0], expression2=Constant[c=4.0]]," +
            " expression2=Negate[expression=Constant[c=1.0]]], power=2.0], expression2=Constant[c=1.0]] = 37.0");
    }
}
