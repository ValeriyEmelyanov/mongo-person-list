package com.example.mongopersonlist.controller;

import com.example.mongopersonlist.dto.middle.PersonPage;
import com.example.mongopersonlist.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public PersonPage getAll(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
                             @RequestParam(name = "size", required = false, defaultValue = "20") int pageSize) {
        return personService.getAllPaginated(pageNumber, pageSize);
    }

}
