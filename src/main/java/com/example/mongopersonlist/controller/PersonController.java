package com.example.mongopersonlist.controller;

import com.example.mongopersonlist.dto.response.PersonPageResponse;
import com.example.mongopersonlist.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person API
 */
@RestController
@RequestMapping("/")
public class PersonController {

    private final PersonService personService;
    private final ConversionService conversionService;

    @Autowired
    public PersonController(PersonService personService, ConversionService conversionService) {
        this.personService = personService;
        this.conversionService = conversionService;
    }

    /**
     * Method for getting a page list of persons.
     * @param pageNumber number of a page
     * @param pageSize size of a page
     * @return a page of persons
     */
    @GetMapping
    public ResponseEntity<PersonPageResponse> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(
                conversionService.convert(
                        personService.getAllPaginated(pageNumber, pageSize),
                        PersonPageResponse.class));
    }

}
