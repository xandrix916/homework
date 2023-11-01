package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Problem2Test {
    @BeforeEach
    void setUp() {
        problem2 = new Problem2();
    }

    private Problem2 problem2;

    @Test
    void firstExample() {
        String input = "()()()";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("()", "()", "()")), response);
    }

    @Test
    void secondExample() {
        String input = "((()))";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(List.of("((()))")), response);
    }

    @Test
    void thirdExample() {
        String input = "((()))(())()()(()())";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("((()))", "(())", "()", "()", "(()())")), response);
    }

    @Test
    void fourthExample() {
        String input = "((())())(()(()()))";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("((())())", "(()(()()))")), response);
    }

    @Test
    void mixTest() {
        String input = "((((()(()()))())))((()))";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("((((()(()()))())))", "((()))")), response);
    }

    @Test
    void oddSymbolsFailure() {
        String input = "(((((*()(-))()+))!(-)))";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(List.of("Program will be stop due" +
            " to odd symbols in given string")), response);
    }

    @Test
    void startEqualsEnding() {
        String input = "((())())(()(()()))(";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(List.of("Program will be stop due to" +
            " an obviously unclusterized sequence in given string")), response);
    }

    @Test
    void oddLength() {
        String input = "((())())(()((()()))";
        List<String> response = problem2.clusterize(input);
        Assertions.assertEquals(new ArrayList<>(List.of("Program will be stop due to" +
            " an obviously unclusterized sequence in given string")), response);
    }
}
