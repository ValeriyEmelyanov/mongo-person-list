package com.example.mongopersonlist.service;

import com.example.mongopersonlist.dto.request.PersonRequest;
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
     * @param id person`s unique identifier
     * @return {@link PersonResponse person} object with unique identifier like in the argument
     * @throws IllegalArgumentException if input id is null
     * @throws PersonNotFoundException  if there is no {@link Person person} object
     *                                  with unique identifier like in the argument
     */
    PersonResponse getById(String id);

    /**
     * Method for saving person the {@link Person person} in the repository.
     *
     * @param personRequest {@link PersonRequest personRequest} object to save
     * @return saved {@link PersonResponse personResponse} object
     * @throws IllegalArgumentException if input {@link PersonRequest personRequest} object is null
     */
    PersonResponse save(PersonRequest personRequest);

    /**
     * Method for updating the {@link Person person} in the repository.
     *
     * @param id            person`s unique identifier
     * @param personRequest {@link PersonRequest personRequest} object to update
     * @return updated {@link PersonResponse personResponse} object
     * @throws IllegalArgumentException if any of input arguments is null
     * @throws PersonNotFoundException  if there is no {@link Person person} object in the repository
     *                                  with unique identifier like in the argument
     */
    PersonResponse update(String id, PersonRequest personRequest);

    /**
     * Method for deleting the {@link Person person} in the repository.
     *
     * @param id person`s unique identifier
     * @throws IllegalArgumentException if input id is null
     * @throws PersonNotFoundException  if there is no {@link Person person} object
     *                                  with unique identifier like in the argument
     */
    void delete(String id);
}
