package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem14Test {
    @BeforeEach
    void setUp() {
        problem14 = new Problem14();
    }

    private Problem14 problem14;

    @Test
    void thereIsDog() {
        List<Animal> doggosList = new ArrayList<>(List.of(
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Ludwig", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)));
        Boolean response = problem14.isThereDogHigherThanK(doggosList, 65);
        assertTrue(response);
    }

    @Test
    void thereAreNoDogs() {
        List<Animal> doggosList = new ArrayList<>(List.of(
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Ludwig", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)));
        Boolean response = problem14.isThereDogHigherThanK(doggosList, 80);
        assertFalse(response);
    }
}
