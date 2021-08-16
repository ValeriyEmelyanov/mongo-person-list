package com.example.mongopersonlist.service.impl;

import com.example.mongopersonlist.dao.PersonDao;
import com.example.mongopersonlist.dto.middle.PersonPage;
import com.example.mongopersonlist.model.Person;
import com.example.mongopersonlist.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    @NonNull
    public PersonPage getAllPaginated(int pageNumber, int pageSize) {
        log.info("Requested person page: {} page, {} size", pageNumber, pageSize);

        long totalElements = personDao.size();
        int totalPages = (int) (totalElements / pageSize + (totalElements % pageSize == 0 ? 0 : 1));
        List<Person> persons;
        if (totalElements < (long) (pageNumber - 1) * pageSize) {
            persons = List.of();
        } else {
            persons = personDao.getAllPaginated(pageNumber, pageSize);
        }
        return new PersonPage(persons, pageSize, pageNumber, totalPages, totalElements);
    }
}
