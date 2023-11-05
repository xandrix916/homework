package edu.hw4;

import java.util.List;

public class Problem12 {
    public Integer problematicBodyMassIndex(List<Animal> animalList) {
        return animalList.stream().filter(k -> k.weight() > k.height()).toList().size();
    }
}
