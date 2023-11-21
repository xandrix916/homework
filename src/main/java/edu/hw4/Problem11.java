package edu.hw4;

import java.util.List;

public class Problem11 {
    public static final int CONFIGURATION_HEIGHT = 100;

    public List<Animal> whoBitesAndHigherThat100(List<Animal> animals) {
        return animals.stream()
            .filter(k -> k.bites() && k.height() > CONFIGURATION_HEIGHT)
            .toList();
    }
}
