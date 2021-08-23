package com.example.mongopersonlist.service.impl;

import com.example.mongopersonlist.dao.PersonDao;
import com.example.mongopersonlist.dto.response.PersonPageResponse;
import com.example.mongopersonlist.dto.response.PersonResponse;
import com.example.mongopersonlist.exception.PersonNotFoundException;
import com.example.mongopersonlist.model.Person;
import com.example.mongopersonlist.service.PersonService;
import com.example.mongopersonlist.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    private final ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    @NonNull
    public PersonPageResponse getAllPaginated(int pageNumber, int pageSize) {
        log.info("Requested person page: {} page, {} size", pageNumber, pageSize);

        long totalElements = personDao.size();
        int totalPages = (int) (totalElements / pageSize + (totalElements % pageSize == 0 ? 0 : 1));
        List<Person> persons;
        if (totalElements < (long) (pageNumber - 1) * pageSize) {
            persons = List.of();
        } else {
            persons = personDao.getAllPaginated(pageNumber, pageSize);
        }
        return PersonPageResponse.builder()
                .persons(persons.stream()
                        .map(p -> conversionService.convert(p, PersonResponse.class))
                        .collect(Collectors.toList()))
                .size(pageSize)
                .number(pageNumber)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    @NonNull
    public PersonResponse getById(@NonNull String id) {
        Assert.notNull(id, ErrorMessages.PERSON_ID_NOT_NULL);
        log.info("Requested The Person with id: {}", id);

        Person person = personDao.getById(id);
        if (person == null) {
            throw new PersonNotFoundException(String.format("Person with id: %s is not found", id));
        }
        return Objects.requireNonNull(conversionService.convert(person, PersonResponse.class));
    }
}
