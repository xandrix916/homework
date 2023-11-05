package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Problem8Test {
    @BeforeEach
    void setUp() {
        problem8 = new Problem8();
    }

    private Problem8 problem8;

    List<Animal> animalList = Problem7Test.animalList;

    @Test
    void lowerAndLower() {
        Optional<Animal> response = problem8.oldestFromLowerK(animalList, 200);
        assertEquals(Optional.of(animalList.get(9)), response);
    }

    @Test
    void notThisLow() {
        Optional<Animal> response = problem8.oldestFromLowerK(animalList, 10);
        assertEquals(Optional.empty(), response);
    }


}
