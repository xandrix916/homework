package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem20Test {
    @BeforeEach
    void setUp() {
        problem20 = new Problem20();
    }

    private Problem20 problem20;

    @Test
    void listOfProblems() {
        // Arrange
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 23, 24,0, true),
            new Animal("Ginger", Animal.Type.CAT, null, 100500, 40, 10, true),
            new Animal("Bunny", null, Animal.Sex.F, 12, 1000000000, 14000, false)
        ));

        // Act
        var response = problem20.getErrors(animalList);

        // Assert
        assertEquals(new HashMap<>(){{
            put("", ValidationError.setToString(animalList.get(0)));
            put("Ginger", ValidationError.setToString(animalList.get(1)));
            put("Bunny", ValidationError.setToString(animalList.get(2)));
        }}, response);
    }

}
