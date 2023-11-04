package edu.hw4;

import java.util.Comparator;

public class WeightComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        return o2.weight() - o1.weight();
    }
}
