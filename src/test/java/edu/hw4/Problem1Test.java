package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem1Test {
    @BeforeEach
    void setUp() {
        problem1 = new Problem1();
    }

    private Problem1 problem1;

    @Test
    void knownDogs() {
        List<Animal> doggosList = new ArrayList<>(List.of(
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Ludwig", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)));
        List<Animal> response = problem1.sortByHeight(doggosList);
        assertEquals(new ArrayList<>(List.of(
            new Animal("Ludwig", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false)
            )),
            response);
    }

    @Test
    void hollowTest() {
        List<Animal> animalList = new ArrayList<>();
        List<Animal> response = problem1.sortByHeight(animalList);
        assertEquals(new ArrayList<>(), response);
    }
}
