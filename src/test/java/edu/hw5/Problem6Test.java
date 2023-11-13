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
        String potentialSub = "abc";
        String sequence = "achfdbaabgabcaabg";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertTrue(response);
    }

    @Test
    void oneMoreTest() {
        String potentialSub = " ip";
        String sequence = "Lorem ipsum dolor set";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertTrue(response);
    }

    @Test
    void subMoreThanSeq() {
        String potentialSub = "achfdbaabgabcaabg";
        String sequence = "abc";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertFalse(response);
    }

    @Test
    void subEqualsSeq() {
        String potentialSub = "abc";
        String sequence = "abc";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertTrue(response);
    }

    @Test
    void hollowSub() {
        String potentialSub = "";
        String sequence = "abc";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertTrue(response);
    }

    @Test
    void hollowSeq() {
        String potentialSub = "abc";
        String sequence = "";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertFalse(response);
    }

    @Test
    void hollowSeqAndSub() {
        String potentialSub = "";
        String sequence = "";
        boolean response = problem6.isSubsequence(sequence, potentialSub);
        assertTrue(response);
    }

}
