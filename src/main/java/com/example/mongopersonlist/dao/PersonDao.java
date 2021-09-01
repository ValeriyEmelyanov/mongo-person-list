package com.example.mongopersonlist.dao;


import com.example.mongopersonlist.model.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * Data access object layer for working with Person collection.
 */
public interface PersonDao {

    /**
     * Mothod for saving a new Person.
     *
     * @param person a person to save
     * @return saved person
     */
    Person save(Person person);

    /**
     * Method for getting the full list of persons.
     *
     * @return the full list of persons
     */
    List<Person> getAll();

    /**
     * Method for getting a page list of persons.
     *
     * @param pageNumber number of a page
     * @param pageSize   size of a page
     * @return a list of persons for one page
     */
    List<Person> getAllPaginated(int pageNumber, int pageSize);

    /**
     * Method for finding the {@link Person person} by its unique identifier.
     *
     * @param id person`s unique identifier
     * @return {@link Person person} object with unique identifier like in the argument
     * or null if no person with such identifier
     */
    Person getById(String id);

    /**
     * Method for getting some person by a email.
     *
     * @param email email of person which is finding
     * @return person with enail like in the argument
     * or null if no person with such email
     */
    Person getByEmail(String email);

    /**
     * Method for getting some person by a name.
     *
     * @param name name of person which is finding
     * @return person with name like in the argument
     */
    Person findOneByName(String name);

    /**
     * Method for getting size of the Person collection.
     *
     * @return size of the Person collection
     */
    long size();

    /**
     * Method for getting a list of persons with name like in the argument.
     *
     * @param name name of persons which is finding
     * @return a list of persons with name like in the argument
     */
    List<Person> findByName(String name);

    /**
     * Method for getting a list of persons with birth date after the argument date.
     *
     * @param date after that person birth dates must be
     * @return a list of persons with birth date after the argument date
     */
    List<Person> findByBirthDateAfter(LocalDate date);

    /**
     * Method for getting a list of persons with a favotite book like in the argument.
     *
     * @param book a favorite book
     * @return a list of persons with a favotite book like in the argument
     */
    List<Person> findByFavoriteBook(String book);

    /**
     * Method for updating a Person.
     *
     * @param person a person to update
     * @return updated person
     */
    Person update(Person person);

    /**
     * Method for updating a Person.
     *
     * @param person a person to delete
     */
    void delete(Person person);

    /**
     * Method for deleting all persons from Person collection.
     */
    void deleteAll();
}
