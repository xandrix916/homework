package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {
    @BeforeEach
    void setUp() {
        problem4 = new Problem4();
    }

    private Problem4 problem4;

    @Test
    void simpleValid() {
        // arrange
        String password = "MyFavouritePassword!";

        // act
        boolean response = problem4.passwordMatch(password);

        // assert
        assertTrue(response);
    }

    @Test
    void anotherValid() {
        // arrange
        String password = "~ha!ha@ha#ha$ha%ha^ha&ha*ha|";

        // act
        boolean response = problem4.passwordMatch(password);

        // assert
        assertTrue(response);
    }

    @Test
    void veryBadPassword() {
        // arrange
        String password = "1234";

        // act
        boolean response = problem4.passwordMatch(password);

        // assert
        assertFalse(response);
    }

    @Test
    void notSoBadButStill() {
        // arrange
        String password = "wd2783hdjwh23+++";

        // act
        boolean response = problem4.passwordMatch(password);

        // assert
        assertFalse(response);
    }
}

