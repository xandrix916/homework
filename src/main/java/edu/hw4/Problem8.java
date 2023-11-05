package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Problem8 {
    public Optional<Animal> oldestFromLowerK(List<Animal> animalList, int k) {
        return animalList.stream().filter(animal -> animal.height() < k).max(Comparator.comparing(Animal::weight));
    }
}
