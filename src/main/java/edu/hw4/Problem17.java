package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Problem17 {
    public Boolean spidersOrDogs(List<Animal> animalList) {
        if (animalList.stream().filter(animal -> animal.type() == Animal.Type.DOG).toList().size() == 0
        || animalList.stream().filter(animal -> animal.type() == Animal.Type.SPIDER).toList().size() == 0) {
            return false;
        }
        Map<Animal.Type, Long> typeBites = animalList.stream().filter(animal -> animal.type() == Animal.Type.DOG
            || animal.type() == Animal.Type.SPIDER && animal.bites()).collect(Collectors.groupingBy(Animal::type,
            Collectors.counting()));
        return typeBites.get(Animal.Type.SPIDER) > typeBites.get(Animal.Type.DOG);
    }
}
