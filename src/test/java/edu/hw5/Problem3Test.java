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
        // arrange
        String toParse = "2020-10-10";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.of(2020, 10, 10)), response);
    }

    @Test
    void anotherAmericanExample() {
        // arrange
        String toParse = "2020-12-2";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.of(2020, 12, 2)), response);
    }

    @Test
    void literallyOceanicExample() {
        // arrange
        String toParse = "1/5/1984";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.of(1984, 5, 1)), response);
    }

    @Test
    void anotherEuropeanExample() {
        // arrange
        String toParse = "1/3/20";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.of(2020, 3, 1)), response);
    }

    @Test
    void wordCommand() {
        // arrange
        String toParse = "today";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.now()), response);
    }

    @Test
    void anotherWordCommand() {
        // arrange
        String toParse = "tomorrow";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.now().plusDays(1)), response);
    }

    @Test
    void wordAndNumberCommand() {
        // arrange
        String toParse = "1 day ago";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.now().minusDays(2)), response);
    }

    @Test
    void anotherWordAndNumberCommand() {
        // arrange
        String toParse = "2234 days after";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.of(LocalDate.now().plusDays(2235)), response);
    }

    @Test
    void fallTest() {
        // arrange
        String toParse = "12th December 2022";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.empty(), response);
    }

    @Test
    void anotherFallTest() {
        // arrange
        String toParse = "120 days afterwards";

        // act
        Optional<LocalDate> response = problem3.parseDate(toParse);

        // assert
        assertEquals(Optional.empty(), response);
    }
}
