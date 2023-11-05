package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem11Test {
    @BeforeEach
    void setUp() {
        problem11 = new Problem11();
    }

    private Problem11 problem11;

    @Test
    void badGuy() {
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("Bull Shark", Animal.Type.FISH, Animal.Sex.M,
                50, 225, 95, true)
        ));
        assertEquals(expected, problem11.whoBitesAndHigherThat100(Problem7Test.animalList));
    }

}
