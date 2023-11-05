package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Problem7 {
    public Animal getKthOldestAnimal(List<Animal> animalList, int k) {
        var animals = animalList.stream().sorted(Comparator.comparing(Animal::age)).toList();
        if (k >= animals.size() || k < 0) {
            return null;
        }
        return animals.get(animals.size() - k - 1);
    }
}
