package edu.hw7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {
    private static final String longFactorial = "57133839564458545904789328652610" +
        "5400318955357860112641825483758331798" +
        "2912484539839312657448867531114537710" +
        "7878746854204162666250198684504466355" +
        "94919592206657494259209573577892932" +
        "535729044496247240541679072211844543" +
        "7122269675520000000000000000000000000000000000000";

    @BeforeEach
    void setUp() {
        problem2 = new Problem2();
    }

    private Problem2 problem2;

    @Test
    void easyToCount() {
        // arrange
        int n = 5;

        // act
        BigInteger factorial = problem2.getFactorial(n);

        // assert
        assertEquals(new BigInteger("120"), factorial);
    }

    @Test
    void notSoEasyToCount() {
        // arrange
        int n = 150;

        // act
        BigInteger factorial = problem2.getFactorial(n);

        // assert
        assertEquals(new BigInteger(longFactorial), factorial);
    }

    @Test
    void unusualCountOne() {
        // arrange
        int n = 1;

        // act
        BigInteger factorial = problem2.getFactorial(n);

        // assert
        assertEquals(new BigInteger("1"), factorial);
    }

    @Test
    void unusualCountZero() {
        // arrange
        int n = 0;

        // act
        BigInteger factorial = problem2.getFactorial(n);

        // assert
        assertEquals(new BigInteger("1"), factorial);
    }

    @Test
    void absoluteFail() {
        // arrange
        int n = -273;

        // act
        BigInteger factorial = problem2.getFactorial(n);

        // assert
        assertNull(factorial);
    }
}
