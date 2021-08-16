package com.example.mongopersonlist.dto.middle;

import com.example.mongopersonlist.model.Person;
import lombok.Data;

import java.util.List;

/**
 * An object for trancferring data from a servise layer to a controller layer
 * about a page of persons.
 */
@Data
public class PersonPage {

    /**
     * List of persons.
     */
    private final List<Person> persons;

    /**
     * Page size.
     */
    private final int size;

    /**
     * Page number.
     */
    private final int number;

    /**
     * Total number of pages.
     */
    private final int totalPages;

    /**
     * Total number of elements.
     */
    private final long totalElements;
}
