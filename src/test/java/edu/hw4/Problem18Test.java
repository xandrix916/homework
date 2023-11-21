package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem18Test {
    @BeforeEach
    void setUp() {
        problem18 = new Problem18();
    }

    private Problem18 problem18;

    @Test
    void freddyTheFish() {
        // Arrange
        var animalList = Problem7Test.animalList;
        List<List<Animal>> animalLists = new ArrayList<>(List.of(
            animalList,
            new ArrayList<>(List.of(
                new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
                new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
                new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
                new Animal("Pike", Animal.Type.FISH, Animal.Sex.F,
                    10, 90, 8, false),
                new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M, 20, 24, 0, true)
            )
            )
        ));

        // Act
        Animal response = problem18.heaviestFish(animalLists);

        // Assert
        assertEquals(animalList.get(5), response);
    }

}
