package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem13Test {
    @BeforeEach
    void setUp() {
        problem13 = new Problem13();
    }

    private Problem13 problem13;

    @Test
    void royalPets() {
        // Arrange
        var animals = new ArrayList<>(List.of(
            new Animal("Kitty Softpaws, Legend of Spain", Animal.Type.CAT, Animal.Sex.F, 13, 41, 12, true),
            new Animal("Puss in Boots", Animal.Type.CAT, Animal.Sex.M, 13, 45, 13, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Dori", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Lady", Animal.Type.DOG, Animal.Sex.F, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Sweet Shalquoir of Majula", Animal.Type.CAT, Animal.Sex.F, 12, 30, 6, false)
        ));

        // Act
        var response = problem13.complexNames(animals);

        // Assert
        assertEquals(new ArrayList<>(List.of(
            animals.get(0),
            animals.get(1),
            animals.get(6)
        )), response);
    }
}
