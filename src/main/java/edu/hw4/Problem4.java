package edu.hw4;

import java.util.List;
import java.util.NoSuchElementException;

public class Problem4 {
    public Animal getAnimalWithLargestName(List<Animal> animalList) {
        try {
            return animalList.stream().max(new NameComparator()).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
