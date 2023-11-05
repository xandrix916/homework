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
        assertEquals(2, problem12.problematicBodyMassIndex(Problem7Test.animalList));
    }
}
