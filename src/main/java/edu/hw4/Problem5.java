package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem5 {
    public Animal.Sex getSexPrimacy(List<Animal> animalList) {
        if (animalList.size() == 0) {
            return null;
        }
        Map<Animal.Sex, Long> amountMap = animalList.stream().collect(Collectors.groupingBy(Animal::sex,
            Collectors.mapping(Animal::name, Collectors.counting())));
        return (amountMap.get(Animal.Sex.M) > amountMap.get(Animal.Sex.F) ? Animal.Sex.M : Animal.Sex.F);
    }
}
