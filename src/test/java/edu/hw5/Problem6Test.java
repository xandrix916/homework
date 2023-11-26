package edu.hw5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem6Test {
    @BeforeEach
    void setUp() {
        problem6 = new Problem6();
    }

    private Problem6 problem6;

    @Test
    void exampleTest() {
        // arrange
        String potentialSub = "abc";
        String sequence = "achfdbaabgabcaabg";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertTrue(response);
    }

    @Test
    void oneMoreTest() {
        // arrange
        String potentialSub = " ip";
        String sequence = "Lorem ipsum dolor set";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertTrue(response);
    }

    @Test
    void subMoreThanSeq() {
        // arrange
        String potentialSub = "achfdbaabgabcaabg";
        String sequence = "abc";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertFalse(response);
    }

    @Test
    void subEqualsSeq() {
        // arrange
        String potentialSub = "abc";
        String sequence = "abc";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertTrue(response);
    }

    @Test
    void hollowSub() {
        // arrange
        String potentialSub = "";
        String sequence = "abc";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertTrue(response);
    }

    @Test
    void hollowSeq() {
        // arrange
        String potentialSub = "abc";
        String sequence = "";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertFalse(response);
    }

    @Test
    void hollowSeqAndSub() {
        // arrange
        String potentialSub = "";
        String sequence = "";

        // act
        boolean response = problem6.isSubsequence(sequence, potentialSub);

        // assert
        assertTrue(response);
    }

}
