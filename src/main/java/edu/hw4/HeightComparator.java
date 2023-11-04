package edu.hw4;

import java.util.Comparator;

public class HeightComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.height() - o2.height();
    }
}
