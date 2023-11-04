package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {
    @BeforeEach
    void setUp() {
        problem3 = new Problem3();
    }

    private Problem3 problem3;

    @Test
    void typeAmounts() {
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
        Map<Animal.Type, Integer> amounts = problem3.getMapOfTypeAmounts(animalList);
        Map<Animal.Type, Integer> expected = new HashMap<>(){{
            put(Animal.Type.FISH, 1);
            put(Animal.Type.DOG, 4);
            put(Animal.Type.CAT, 1);
            put(Animal.Type.BIRD, 1);
            put(Animal.Type.SPIDER, 1);
        }};
        assertEquals(expected, amounts);
    }

}
