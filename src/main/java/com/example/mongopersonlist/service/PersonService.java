package com.example.mongopersonlist.service;

import com.example.mongopersonlist.dto.response.PersonPageResponse;
import com.example.mongopersonlist.dto.response.PersonResponse;
import com.example.mongopersonlist.exception.PersonNotFoundException;
import com.example.mongopersonlist.model.Person;

public interface PersonService {

    /**
     * Method for getting a page list of persons.
     *
     * @param pageNumber number of a page
     * @param pageSize   size of a page
     * @return a page of {@link Person person}s
     */
    PersonPageResponse getAllPaginated(int pageNumber, int pageSize);

    /**
     * Method for finding the {@link Person person} by its unique identifier.
     *
     * @param id unique identifier
     * @return {@link PersonResponse person} object with unique identifier like in the argument
     * @throws IllegalArgumentException if input id is null
     * @throws PersonNotFoundException  if there is no {@link Person person} object
     *                                  with unique identifier like in the argument
     */
    PersonResponse getById(String id);
}
