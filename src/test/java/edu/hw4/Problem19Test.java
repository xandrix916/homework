package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem19Test {
    @BeforeEach
    void setUp() {
        problem19 = new Problem19();
    }

    private Problem19 problem19;

    @Test
    void listOfProblems() {
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 23, 24,0, true),
            new Animal("Ginger", Animal.Type.CAT, null, 100500, 40, 10, true),
            new Animal("Bunny", null, Animal.Sex.F, 12, 1000000000, 14000, false)
        ));
        var response = problem19.getErrors(animalList);

        assertEquals(new HashMap<>(){{
            put("", ValidationError.checkError(animalList.get(0)));
            put("Ginger", ValidationError.checkError(animalList.get(1)));
            put("Bunny", ValidationError.checkError(animalList.get(2)));
        }}, response);
    }

}
