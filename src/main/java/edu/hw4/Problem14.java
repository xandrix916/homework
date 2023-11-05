package edu.hw4;

import java.util.List;

public class Problem14 {
    public Boolean isThereDogHigherThanK(List<Animal> animalList, int height) {
        return !animalList.stream().filter(k -> k.type() == Animal.Type.DOG && k.height() > height).toList().isEmpty();
    }
}
