package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    @BeforeEach
    void setUp() {
        problem2 = new Problem2();
    }

    private Problem2 problem2;

    @Test
    void isItFriday() {
        LocalDate localDate = LocalDate.of(2023, 11, 17);
        boolean response = problem2.isFriday(localDate);
        assertTrue(response);
    }

    @Test
    void checkNextYear() {
        int year = 2024;
        String response = problem2.getFridaysByYear(year).toString();
        assertEquals("[2024-09-13, 2024-12-13]", response);
    }

    @Test
    void check1925() {
        int year = 1925;
        String response = problem2.getFridaysByYear(year).toString();
        assertEquals("[1925-02-13, 1925-03-13, 1925-11-13]", response);
    }

    @Test
    void whatWillBeNextFriday13() {
        LocalDate currentDate = LocalDate.of(2023, 11, 13);
        String response = problem2.closestFriday13(currentDate).toString();
        assertEquals("2024-09-13", response);
    }

    @Test
    void itWasLiterallyThisYear() {
        LocalDate currentDate = LocalDate.of(1984, 5, 1);
        String response = problem2.closestFriday13(currentDate).toString();
        assertEquals("1984-07-13", response);
    }
}
