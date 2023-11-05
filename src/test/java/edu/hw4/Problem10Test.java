package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem10Test {

    @BeforeEach
    void setUp() {
        problem10 = new Problem10();
    }

    private Problem10 problem10;

    @Test
    void noMatches() {
        var response = problem10.pawsNotEqualsAge(Problem7Test.animalList);
        assertEquals(Problem7Test.animalList, response);
    }

    @Test
    void notEssentials() {
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 4, 41, 15, true),
            new Animal("Siberian cat", Animal.Type.CAT, Animal.Sex.M, 10, 41, 7, true),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 4, 80, 55, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 2, 250, 156, false),
            new Animal("Hummingbird", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 0, false),
            new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
                0, 1300, 12000, false),
            new Animal("Bull Shark", Animal.Type.FISH, Animal.Sex.M,
                50, 225, 95, true),
            new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M,
                8, 24, 0, true),
            new Animal("Sheepdog", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Senbernar", Animal.Type.DOG, Animal.Sex.M, 4, 75, 90, false),
            new Animal("Berger Picard", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)
        )
        );
        List<Animal> expected = new ArrayList<>(List.of(
            new Animal("Siberian cat", Animal.Type.CAT, Animal.Sex.M, 10, 41, 7, true),
            new Animal("Hummingbird", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 0, false),
            new Animal("Bull Shark", Animal.Type.FISH, Animal.Sex.M,
                50, 225, 95, true),
            new Animal("Sheepdog", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
            new Animal("Berger Picard", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)
        )
        );
        var response = problem10.pawsNotEqualsAge(animalList);
        assertEquals(expected, response);
    }
}
