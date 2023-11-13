package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Problem7Test {

    @BeforeEach
    void setUp() {
        problem7 = new Problem7();
    }

    private Problem7 problem7;

    public static final List<Animal> animalList = new ArrayList<>(List.of(
        new Animal("Mayne Coon", Animal.Type.CAT, Animal.Sex.M, 13, 41, 15, true),
        new Animal("Siberian cat", Animal.Type.CAT, Animal.Sex.M, 10, 41, 7, true),
        new Animal("Irish Wolfhound", Animal.Type.DOG, Animal.Sex.M, 8, 80, 55, true),
        new Animal("Ostrich", Animal.Type.BIRD, Animal.Sex.M, 34, 250, 156, false),
        new Animal("Hummingbird", Animal.Type.BIRD, Animal.Sex.M, 7, 20, 0, false),
        new Animal("Giant Shark", Animal.Type.FISH, Animal.Sex.F,
            100, 1300, 12000, false),
        new Animal("Bull Shark", Animal.Type.FISH, Animal.Sex.M,
            50, 225, 95, true),
        new Animal("Goliath birdeater", Animal.Type.SPIDER, Animal.Sex.M,
            20, 24, 0, true),
        new Animal("Sheepdog", Animal.Type.DOG, Animal.Sex.M, 10, 64, 35, true),
        new Animal("Senbernar", Animal.Type.DOG, Animal.Sex.M, 5, 75, 90, false),
        new Animal("Berger Picard", Animal.Type.DOG, Animal.Sex.M, 14, 62, 26, true)
    )
    );

    @Test
    void oldestOne() {
        var animalList = Problem7Test.animalList.subList(0, 10);
        Animal response = problem7.getKthOldestAnimal(animalList, 2);
        assertEquals(animalList.get(3), response);
    }

    @Test
    void wrongIndex() {
        var animalList = Problem7Test.animalList.subList(0, 10);
        Animal response = problem7.getKthOldestAnimal(animalList, 29);
        assertNull(response);
    }

}
