package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Problem3 {
    public Map<Animal.Type, Integer> getMapOfTypeAmounts(List<Animal> animals) {
        Map<Animal.Type, Long> preOrderedMap = animals.stream().collect(Collectors.groupingBy(Animal::type,
            Collectors.mapping(Animal::name, Collectors.counting())));
        Map<Animal.Type, Integer> resultMap = new HashMap<>();
        preOrderedMap.forEach((k, v) -> resultMap.put(k, v.intValue()));
        return resultMap;
    }
}
