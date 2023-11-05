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
        var expected = new HashMap<>(){{
            put(Animal.Type.CAT, 22);
            put(Animal.Type.DOG, 61);
            put(Animal.Type.SPIDER, 0);
        }};
        assertEquals(expected, problem15.weightForTypeAndAgeRange(Problem7Test.animalList, 10, 20));
    }

}
