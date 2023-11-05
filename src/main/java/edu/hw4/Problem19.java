package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem19 {
    public Map<String, Set<ValidationError>> getErrors(List<Animal> animalList) {
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();
        animalList.forEach(animal -> errorsMap.put(animal.name(), ValidationError.checkError(animal)));
        return errorsMap;
    }
}
