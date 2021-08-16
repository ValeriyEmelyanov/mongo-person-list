package com.example.mongopersonlist.service;

import com.example.mongopersonlist.dto.middle.PersonPage;
import com.example.mongopersonlist.model.Person;

import java.util.List;

public interface PersonService {

    /**
     * Method for getting a page list of persons.
     * @param pageNumber number of a page
     * @param pageSize size of a page
     * @return a page of persons
     */
    PersonPage getAllPaginated(int pageNumber, int pageSize);

}
