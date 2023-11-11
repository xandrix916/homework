package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Problem6Test {

    @BeforeEach
    void setUp() {
        problem6 = new Problem6();
    }

    private Problem6 problem6;

    @Test
    void heavyAnimals() {
        List<Animal> animalList = Problem7Test.animalList;
        Map<Animal.Type, Animal> response = problem6.heavyByType(animalList);
        assertEquals(new HashMap<>() {{
            put(Animal.Type.CAT, animalList.get(0));
            put(Animal.Type.DOG, animalList.get(9));
            put(Animal.Type.BIRD, animalList.get(3));
            put(Animal.Type.FISH, animalList.get(5));
            put(Animal.Type.SPIDER, animalList.get(7));
        }}, response);
    }
}
