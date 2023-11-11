package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Problem12Test {

    @BeforeEach
    void setUp() {
        problem12 = new Problem12();
    }

    private Problem12 problem12;

    @Test
    void countThemAll() {
        var animalList = Problem7Test.animalList;
        Integer response =problem12.problematicBodyMassIndex(animalList);
        assertEquals(2, response);
    }
}
