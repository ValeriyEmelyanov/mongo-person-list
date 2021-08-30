package com.example.mongopersonlist.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a page of persons.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
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
