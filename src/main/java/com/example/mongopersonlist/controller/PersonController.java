package com.example.mongopersonlist.controller;

import com.example.mongopersonlist.dto.response.PersonPageResponse;
import com.example.mongopersonlist.dto.response.PersonResponse;
import com.example.mongopersonlist.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PersonController {

    private final PersonService personService;

    /**
     * Method for getting a page list of persons.
     *
     * @param pageNumber number of a page
     * @param pageSize   size of a page
     * @return a page of persons
     */
    @GetMapping
    public ResponseEntity<PersonPageResponse> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(personService.getAllPaginated(pageNumber, pageSize));
    }

    /**
     * Method for finding the person by its unique identifier.
     *
     * @param id unique identifier
     * @return {@link PersonResponse person} object with unique identifier like in the argument
     */
    @GetMapping("{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(personService.getById(id));
    }
}
