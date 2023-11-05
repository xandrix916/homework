package edu.hw4;

import java.util.List;

public class Problem13 {
    public List<Animal> complexNames(List<Animal> animals) {
        return animals.stream().filter(
            animal -> animal.name().indexOf(' ') != animal.name().lastIndexOf(' ')).toList();
    }
}
