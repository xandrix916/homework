package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem19 {
    public Map<String, Set<ValidationError>> getErrors(List<Animal> animalList) {
        return animalList.stream()
            .collect(Collectors.toMap(Animal::name, ValidationError::checkError));
    }
}
