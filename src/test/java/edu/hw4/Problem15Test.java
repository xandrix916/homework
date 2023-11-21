package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class Problem15Test {
    @BeforeEach
    void setUp() {
        problem15 = new Problem15();
    }

    private Problem15 problem15;

    @Test
    void simpleTest() {
        // Arrange
        var animalList = Problem7Test.animalList;

        // Act
        var response = problem15.weightForTypeAndAgeRange(animalList, 10, 20);

        // Assert
        assertEquals(new HashMap<>(){{
            put(Animal.Type.CAT, 22);
            put(Animal.Type.DOG, 61);
            put(Animal.Type.SPIDER, 0);
        }}, response);
    }

}
