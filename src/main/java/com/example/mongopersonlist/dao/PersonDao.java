package com.example.mongopersonlist.dao;


import com.example.mongopersonlist.model.Person;

import java.util.List;

public interface PersonDao {
    Person save(Person person);
    List<Person> getAll();
    List<Person> getAllPaginated(int pageNumber, int pageSize);
    Person findOneByName(String name);
    List<Person> findByName(String name);
    Person update(Person person);
    void delete(Person person);
    void deleteAll();
}
