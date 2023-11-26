package edu.hw7;

import java.util.List;

@SuppressWarnings("unused")
public class Problem3 {
    public void findSomeone(List<Person> people, String name, String address, String phone) {
        PersonDataBase dataBase = new ServiceDataBase();
        people.parallelStream().forEach(dataBase::add);

    }
}
