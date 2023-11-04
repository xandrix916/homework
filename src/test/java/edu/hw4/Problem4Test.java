package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem4Test {
    @BeforeEach
    void setUp() {
        problem4 = new Problem4();
    }

    private Problem4 problem4;

    @Test
    void theLargestOne() {
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M, 20, 24, 0, true),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Ludwig", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)
        )
        );
        Animal response = problem4.getAnimalWithLargestName(animalList);
        assertEquals(animalList.get(4), response);
    }

    @Test
    void somethingWentWrong() {
        List<Animal> animalList = new ArrayList<>();
        Animal largestOne = problem4.getAnimalWithLargestName(animalList);
        assertNull(largestOne);
    }
}
