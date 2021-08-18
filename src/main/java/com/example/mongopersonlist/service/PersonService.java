package com.example.mongopersonlist.service;

import com.example.mongopersonlist.dto.response.PersonPageResponse;

public interface PersonService {

    /**
     * Method for getting a page list of persons.
     *
     * @param pageNumber number of a page
     * @param pageSize   size of a page
     * @return a page of persons
     */
    PersonPageResponse getAllPaginated(int pageNumber, int pageSize);

}
