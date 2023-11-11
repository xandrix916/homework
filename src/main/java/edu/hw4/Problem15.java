package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem15 {
    public Map<Animal.Type, Integer> weightForTypeAndAgeRange(List<Animal> animalList, int k, int l) {
        return animalList.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }
}
