package edu.hw4;

import java.util.List;

public class Problem1 {
    public List<Animal> sortByHeight(List<Animal> animalList) {
        return animalList.stream().sorted(new HeightComparator()).toList();
    }
}
