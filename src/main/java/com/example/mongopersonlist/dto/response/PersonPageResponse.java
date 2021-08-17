package com.example.mongopersonlist.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a page of persons.
 */
@Data
@Builder
public class PersonPageResponse {

    /**
     * List of persons.
     */
    private final List<PersonResponse> persons;

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
