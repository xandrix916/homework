package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem9Test {
    @BeforeEach
    void setUp() {
        problem9 = new Problem9();
    }

    private Problem9 problem9;

    @Test
    void lotsOfPaws() {
        // Arrange
        List<Animal> animalList = Problem7Test.animalList;

        // Act
        Integer response = problem9.sumOfPaws(animalList);

        // Assert
        assertEquals(36, response);
    }

    @Test
    void noPawsButStillSomeAnimals() {
        // Arrange
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Bull Shark", Animal.Type.FISH, Animal.Sex.M,
                50, 225, 95, true)
        ));

        // Act
        Integer response = problem9.sumOfPaws(animalList);

        // Assert
        assertEquals(0, response);
    }
}
