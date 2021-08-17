package com.example.mongopersonlist.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a person.
 */
@Data
@Builder
public class PersonResponse {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private List<String> favoriteBooks;
}
