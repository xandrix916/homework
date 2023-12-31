package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {
    @BeforeEach
    void setUp() {
        problem8 = new Problem8();
    }

    private Problem8 problem8;

    @Test
    void lowerAndLower() {
        // Arrange
        List<Animal> animalList = Problem7Test.animalList;

        // Act
        Optional<Animal> response = problem8.oldestFromLowerK(animalList, 200);

        // Assert
        assertEquals(Optional.of(animalList.get(9)), response);
    }

    @Test
    void notThisLow() {
        // Arrange
        List<Animal> animalList = Problem7Test.animalList;

        // Act
        Optional<Animal> response = problem8.oldestFromLowerK(animalList, 10);

        // Assert
        assertEquals(Optional.empty(), response);
    }


}
