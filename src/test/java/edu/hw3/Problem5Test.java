package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Problem5Test {
    @BeforeEach
    void setUp() {
        problem5 = new Problem5();
    }

    private Problem5 problem5;

    @Test
    void lockeAndAquinas() {
        ArrayList<String> originalContacts = new ArrayList<>(Arrays.asList("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"));
        ArrayList<String> response = problem5.parseContacts(originalContacts, Problem5.ASCENDING_WAY);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke")), response);
    }

    @Test
    void erdosAndEuler() {
        ArrayList<String> originalContacts = new ArrayList<>(Arrays.asList("Paul Erdos", "Leonhard Euler", "Carl Gauss"));
        ArrayList<String> response = problem5.parseContacts(originalContacts, Problem5.DESCENDING_WAY);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("Carl Gauss", "Paul Erdos", "Leonhard Euler")), response);
    }

    @Test
    void emptyArray() {
        ArrayList<String> emptyArray = new ArrayList<>();
        ArrayList<String> response = problem5.parseContacts(emptyArray, Problem5.DESCENDING_WAY);
        Assertions.assertEquals(new ArrayList<>(), response);
    }

    @Test
    void nullTest() {
        ArrayList<String> response = problem5.parseContacts(null, Problem5.DESCENDING_WAY);
        Assertions.assertEquals(new ArrayList<>(), response);
    }

    @Test
    void wrongData() {
        ArrayList<String> characterNames = new ArrayList<>(Arrays.asList("CJ", "TommyAngelo", "V"));
        ArrayList<String> response = problem5.parseContacts(characterNames, Problem5.ASCENDING_WAY);
        Assertions.assertEquals(new ArrayList<>(List.of("Given string TommyAngelo doesn't fit " +
            "to \"name surname\" template")), response);
    }
}
