package com.example.mongopersonlist.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * An object for trancferring data from a controller to a response
 * about a person.
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"}, doNotUseGetters = true)
@ToString
@Builder
public class PersonResponse {
    private final String id;
    private final String name;
    private final LocalDate birthDate;
    private final String email;
    private final List<String> favoriteBooks;
}
