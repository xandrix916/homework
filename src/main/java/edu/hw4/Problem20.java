package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem20 {
    public Map<String, String> getErrors(List<Animal> animalList) {
        Map<String, String> errorsMap = new HashMap<>();
        animalList.forEach(animal -> errorsMap.put(animal.name(), ValidationError.setToString(animal)));
        return errorsMap;
    }
}
