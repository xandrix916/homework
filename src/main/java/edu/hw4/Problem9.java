package edu.hw4;

import java.util.List;

public class Problem9 {
    public Integer sumOfPaws(List<Animal> animalList) {
        return animalList.stream().mapToInt(Animal::paws).sum();
    }
}
