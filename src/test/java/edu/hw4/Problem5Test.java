package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem5Test {
    @BeforeEach
    void setUp() {
        problem5 = new Problem5();
    }

    private Problem5 problem5;


    @Test
    void maleTest() {
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
        Animal.Sex response = problem5.getSexPrimacy(animalList);
        assertEquals(Animal.Sex.M, response);
    }

    @Test
    void femaleTest() {
        List<Animal> animalList = new ArrayList<>(List.of(
            new Animal("Kitty Softpaws", Animal.Type.CAT, Animal.Sex.F, 13, 41, 15, true),
            new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
            new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
            new Animal("Dori", Animal.Type.FISH, Animal.Sex.F,
                100, 1300, 12000, false),
            new Animal("Lady", Animal.Type.DOG, Animal.Sex.F, 10, 64, 35, true),
            new Animal("Beethoven", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
            new Animal("Sweet Shalquoir", Animal.Type.CAT, Animal.Sex.F, 12, 30, 6, false)
        )
        );
        Animal.Sex response = problem5.getSexPrimacy(animalList);
        assertEquals(Animal.Sex.F, response);
    }

    @Test
    void thirdGender() {
        List<Animal> animalList = new ArrayList<>();
        var response = problem5.getSexPrimacy(animalList);
        assertNull(response);
    }
}
