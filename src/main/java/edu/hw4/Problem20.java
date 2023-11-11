package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem20 {
    public Map<String, String> getErrors(List<Animal> animalList) {
        return animalList.stream().collect(Collectors.toMap(Animal::name, ValidationError::setToString));
    }
}
