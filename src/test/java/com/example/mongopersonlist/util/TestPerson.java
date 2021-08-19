package com.example.mongopersonlist.util;

import com.example.mongopersonlist.model.Person;

import java.time.LocalDate;
import java.util.List;

public class TestPerson {

    private TestPerson() {
    }

    public static Person personOne() {
        Person person = new Person();
        person.setName("Alex");
        person.setBirthDate(LocalDate.of(1990, 2, 15));
        person.setEmail("alex@gmail.com");
        person.setFavoriteBooks(List.of("Harry potter", "Waking Up"));

        return person;
    }

    public static Person personTwo() {
        Person person = new Person();
        person.setName("Tom");
        person.setBirthDate(LocalDate.of(1993, 7, 21));
        person.setEmail("tom@gmail.com");
        person.setFavoriteBooks(List.of("Secrets of Butene", "Meeting Success"));

        return person;
    }
}
