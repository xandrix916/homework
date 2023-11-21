package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem17Test {
    @BeforeEach
    void setUp() {
        problem17 = new Problem17();
    }

    private Problem17 problem17;

    @Test
    void spidersBitesOften() {
        // Arrange
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
            new Animal("Siberian cat", Animal.Type.CAT, Animal.Sex.M, 10, 41, 7, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Hummingbird", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 0, false),
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Tarantula", Animal.Type.SPIDER, Animal.Sex.M,
                15, 16, 0, true),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M,
                20, 24, 0, true),
            new Animal("Senbernar", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false)
        )
        );

        // Act
        Boolean response = problem17.spidersOrDogs(animalList);

        // Assert
        assertTrue(response);
    }

    @Test
    void butDogsDoToo() {
        // Arrange
        var animalList = Problem7Test.animalList;

        // Act
        Boolean response = problem17.spidersOrDogs(animalList);

        // Assert
        assertFalse(response);
    }

}
