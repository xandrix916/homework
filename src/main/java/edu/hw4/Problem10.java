package edu.hw4;

import java.util.List;

public class Problem10 {
    public List<Animal> pawsNotEqualsAge(List<Animal> animalList) {
        return animalList.stream().filter(k -> k.age() != k.paws()).toList();
    }
}
