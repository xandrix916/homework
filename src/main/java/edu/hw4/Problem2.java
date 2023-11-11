package edu.hw4;

import java.util.ArrayList;
import java.util.List;

public class Problem2 {
    public List<Animal> sortByWeightPickFirstK(List<Animal> animalList, int k) {
        if (k <= 0) {
            return new ArrayList<>();
        }
        List<Animal> animals = animalList.stream()
            .sorted(new WeightComparator())
            .toList();
        return (k <= animals.size() ? animals.subList(0, k) : animals);
    }
}
