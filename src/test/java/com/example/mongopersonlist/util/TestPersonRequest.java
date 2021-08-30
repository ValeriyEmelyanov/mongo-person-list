package com.example.mongopersonlist.util;

import com.example.mongopersonlist.service.request.PersonRequest;

import java.time.LocalDate;
import java.util.List;

public class TestPersonRequest {

    private TestPersonRequest() {
    }

    public static PersonRequest requestOne() {
        return PersonRequest.builder()
                .name("Ivan")
                .birthDate(LocalDate.of(1992, 3, 18))
                .email("ivan@mail.ru")
                .favoriteBooks(List.of("Secrets of Butene", "Meeting Success"))
                .build();
    }
}
