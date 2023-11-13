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
        String password = "MyFavouritePassword!";
        boolean response = problem4.passwordMatch(password);
        assertTrue(response);
    }

    @Test
    void anotherValid() {
        String password = "~ha!ha@ha#ha$ha%ha^ha&ha*ha|";
        boolean response = problem4.passwordMatch(password);
        assertTrue(response);
    }

    @Test
    void veryBadPassword() {
        String password = "1234";
        boolean response = problem4.passwordMatch(password);
        assertFalse(response);
    }

    @Test
    void notSoBadButStill() {
        String password = "wd2783hdjwh23+++";
        boolean response = problem4.passwordMatch(password);
        assertFalse(response);
    }
}

