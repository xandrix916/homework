package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem11Test {
    @BeforeEach
    void setUp() {
        problem11 = new Problem11();
    }

    private Problem11 problem11;

    @Test
    void badGuy() {
        // Arrange
        var animalList = Problem7Test.animalList;

        // Act
        List<Animal> response = problem11.whoBitesAndHigherThat100(animalList);

        // Assert
        assertEquals(new ArrayList<>(List.of(
            new Animal("Bull Shark", Animal.Type.FISH, Animal.Sex.M,
                50, 225, 95, true))), response);
    }

}
