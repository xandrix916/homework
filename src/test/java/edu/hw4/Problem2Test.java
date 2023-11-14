package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {
    @BeforeEach
    void setUp() {
        problem2 = new Problem2();
    }

    private Problem2 problem2;

    @Test
    void zooSpecies() {
        // Arrange
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M, 20, 24, 0, true)
        )
        );

        // Act
        List<Animal> response = problem2.sortByWeightPickFirstK(animalList, 3);

        // Assert
        assertEquals(new ArrayList<>(List.of(
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true)
        )
        ), response);
    }

    @Test
    void tooBigK() {
        // Arrange
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M, 20, 24, 0, true)
        )
        );

        // Act
        List<Animal> response = problem2.sortByWeightPickFirstK(animalList, 7);

        // Assert
        assertEquals(new ArrayList<>(List.of(
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M, 20, 24, 0, true)
        )
        ), response);
    }

    @Test
    void tooSmallK() {
        // Arrange
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M, 20, 24, 0, true)
        )
        );

        // Act
        List<Animal> response = problem2.sortByWeightPickFirstK(animalList, -5);

        // Assert
        assertEquals(new ArrayList<>(), response);
    }
}
