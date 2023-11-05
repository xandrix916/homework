package edu.hw4;

import java.util.List;

public class Problem11 {
    public List<Animal> whoBitesAndHigherThat100(List<Animal> animals) {
        return animals.stream().filter(k -> k.bites() && k.height() > 100).toList();
    }
}
