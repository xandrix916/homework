package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {
    @BeforeEach
    void setUp() {
        problem3 = new Problem3();
    }

    private Problem3 problem3;

    @Test
    void classicalAmericanExample() {
        String toParse = "2020-10-10";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), response);
    }

    @Test
    void anotherAmericanExample() {
        String toParse = "2020-12-2";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), response);
    }

    @Test
    void literallyOceanicExample() {
        String toParse = "1/5/1984";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.of(1984, 5, 1)), response);
    }

    @Test
    void anotherEuropeanExample() {
        String toParse = "1/3/20";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.of(2020, 3, 1)), response);
    }

    @Test
    void wordCommand() {
        String toParse = "today";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.now()), response);
    }

    @Test
    void anotherWordCommand() {
        String toParse = "tomorrow";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), response);
    }

    @Test
    void wordAndNumberCommand() {
        String toParse = "1 day ago";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.now().minusDays(2)), response);
    }

    @Test
    void anotherWordAndNumberCommand() {
        String toParse = "2234 days after";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.of(LocalDate.now().plusDays(2235)), response);
    }

    @Test
    void fallTest() {
        String toParse = "12th December 2022";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.empty(), response);
    }

    @Test
    void anotherFallTest() {
        String toParse = "120 days afterwards";
        Optional<LocalDate> response = problem3.parseDate(toParse);
        assertEquals(Optional.empty(), response);
    }
}
