package com.example.mongopersonlist.service.impl;

import com.example.mongopersonlist.dao.PersonDao;
import com.example.mongopersonlist.dto.response.PersonPageResponse;
import com.example.mongopersonlist.dto.response.PersonResponse;
import com.example.mongopersonlist.model.Person;
import com.example.mongopersonlist.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    private final ConversionService conversionService;

    @Autowired
    public PersonServiceImpl(PersonDao personDao, ConversionService conversionService) {
        this.personDao = personDao;
        this.conversionService = conversionService;
    }

    @Override
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
}
