package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Problem6 {
    public Map<Animal.Type, Animal> heavyByType(List<Animal> animalList) {
        var preMap = animalList.stream().collect(Collectors.groupingBy(Animal::type,
            Collectors.maxBy(Comparator.comparing(Animal :: weight))));
        Map<Animal.Type, Animal> resultMap = new HashMap<>();
        try {
            preMap.forEach((k, v) -> resultMap.put(k, (v.orElse(null))));
            return resultMap;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
