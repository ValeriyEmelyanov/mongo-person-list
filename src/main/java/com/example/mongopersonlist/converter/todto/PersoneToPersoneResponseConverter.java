package com.example.mongopersonlist.converter.todto;

import com.example.mongopersonlist.service.response.PersonResponse;
import com.example.mongopersonlist.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Converter for converting Person to PersonResponse
 */
@Component
public class PersoneToPersoneResponseConverter implements Converter<Person, PersonResponse> {

    @Override
    public PersonResponse convert(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .birthDate(person.getBirthDate())
                .email(person.getEmail())
                .favoriteBooks(List.copyOf(person.getFavoriteBooks()))
                .build();
    }
}
