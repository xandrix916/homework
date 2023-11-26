package edu.hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceDataBase implements PersonDataBase {
    private final Map<String, List<Person>> namePeopleMap = new HashMap<>();
    private final Map<String, List<Person>> addressPeopleMap = new HashMap<>();
    private final Map<String, List<Person>> phonePeopleMap = new HashMap<>();

    private final Map<Integer, Person> idPersonMap = new HashMap<>();

    private void addPerson(Map<String, List<Person>> peopleMap, String key, Person person) {
        if (peopleMap.containsKey(key)) {
            peopleMap.get(key).add(person);
        } else {
            peopleMap.put(key, new ArrayList<>(List.of(person)));
        }
    }


    @Override
    public void add(Person person) {
        synchronized (idPersonMap) {
            idPersonMap.put(person.id(), person);
            synchronized (namePeopleMap) {
                addPerson(namePeopleMap, person.name(), person);
                synchronized (addressPeopleMap) {
                    addPerson(addressPeopleMap, person.address(), person);
                    synchronized (phonePeopleMap) {
                        addPerson(phonePeopleMap, person.phoneNumber(), person);
                    }
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        synchronized (namePeopleMap) {
            namePeopleMap.get(idPersonMap.get(id).name()).removeIf(it -> id == it.id());
            synchronized (addressPeopleMap) {
                addressPeopleMap.get(idPersonMap.get(id).name()).removeIf(it -> it.id() == id);
                synchronized (phonePeopleMap) {
                    phonePeopleMap.get(idPersonMap.get(id).name()).removeIf(it -> it.id() == id);
                    synchronized (idPersonMap) {
                        idPersonMap.remove(id);
                    }
                }
            }
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return namePeopleMap.get(name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return addressPeopleMap.get(address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return phonePeopleMap.get(phone);
    }
}
