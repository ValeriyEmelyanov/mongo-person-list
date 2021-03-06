package com.example.mongopersonlist.converter.toModel;

import com.example.mongopersonlist.service.request.PersonRequest;
import com.example.mongopersonlist.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PersonRequestToPersonConverter implements Converter<PersonRequest, Person> {
    @Override
    public Person convert(PersonRequest personRequest) {
        Person person = new Person();
        person.setName(personRequest.getName());
        person.setBirthDate(personRequest.getBirthDate());
        person.setEmail(personRequest.getEmail());
        person.setFavoriteBooks(new ArrayList<>(personRequest.getFavoriteBooks()));

        return person;
    }
}
