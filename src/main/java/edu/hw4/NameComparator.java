package edu.hw4;

import java.util.Comparator;

public class NameComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.name().length() - o2.name().length();
    }
}
