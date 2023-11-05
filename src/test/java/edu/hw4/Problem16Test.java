package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem16Test {
    @BeforeEach
    void setUp() {
        problem16 = new Problem16();
    }

    private Problem16 problem16;

    @Test
    void unusualSorting() {
        var animals = new ArrayList<>(List.of(
            new Animal("Kitty Softpaws, Legend of Spain", Animal.Type.CAT, Animal.Sex.F, 13, 41, 12, true),
            new Animal("Puss in Boots", Animal.Type.CAT, Animal.Sex.M, 13, 45, 13, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Lady", Animal.Type.DOG, Animal.Sex.F, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Sweet Shalquoir of Majula", Animal.Type.CAT, Animal.Sex.F, 12, 30, 6, false)
        ));
        var expected = new ArrayList<>(List.of(
           animals.get(1),
           animals.get(0),
           animals.get(animals.size() - 1),
           animals.get(4),
           animals.get(3),
           animals.get(2)
        ));
        assertEquals(expected, problem16.sortByTypeSexName(animals));
    }

}
