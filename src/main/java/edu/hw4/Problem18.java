package edu.hw4;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Problem18 {
    public Animal heaviestFish(List<List<Animal>> animalLists) {
        List<Animal> commonList = new ArrayList<>();
        for (var list: animalLists) {
            commonList = Stream.concat(commonList.stream(), list.stream()).toList();
        }
        try {
            return commonList.stream()
                .max(Comparator.comparing(Animal::weight)).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
