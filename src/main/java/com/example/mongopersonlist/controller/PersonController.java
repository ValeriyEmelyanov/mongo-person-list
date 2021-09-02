package com.example.mongopersonlist.controller;

import com.example.mongopersonlist.service.PersonService;
import com.example.mongopersonlist.service.request.PersonRequest;
import com.example.mongopersonlist.service.response.PersonPageResponse;
import com.example.mongopersonlist.service.response.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * Method for saving person in the repository.
     *
     * @param personRequest {@link PersonRequest personRequest} object to save
     * @return saved {@link PersonResponse personResponse} object
     */
    @PostMapping
    public ResponseEntity<PersonResponse> save(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personRequest));
    }

    /**
     * Method for updating the person in the repository.
     *
     * @param id            person`s unique identifier
     * @param personRequest {@link PersonRequest personRequest} object to update
     * @return {@link PersonResponse personResponse} object
     */
    @PutMapping("{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable(name = "id") String id,
                                                 @RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(personService.update(id, personRequest));
    }

    /**
     * Method for deleting the person in the repository.
     *
     * @param id person`s unique identifier
     * @return no content
     */
    @DeleteMapping("{id}")
    public ResponseEntity<PersonResponse> delete(@PathVariable(name = "id") String id) {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
