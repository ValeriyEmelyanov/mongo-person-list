package com.example.mongopersonlist.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(doNotUseGetters = true)
@ToString
@Builder
public class PersonRequest {
    private final String name;
    private final LocalDate birthDate;
    private final String email;
    private final List<String> favoriteBooks;
}
