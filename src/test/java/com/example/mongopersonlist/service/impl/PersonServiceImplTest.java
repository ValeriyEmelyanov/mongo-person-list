package com.example.mongopersonlist.service.impl;

import com.example.mongopersonlist.converter.toModel.PersonRequestToPersonConverter;
import com.example.mongopersonlist.converter.todto.PersoneToPersoneResponseConverter;
import com.example.mongopersonlist.dao.PersonDao;
import com.example.mongopersonlist.exception.EmailAlreadyTakenException;
import com.example.mongopersonlist.exception.PersonNotFoundException;
import com.example.mongopersonlist.model.Person;
import com.example.mongopersonlist.service.request.PersonRequest;
import com.example.mongopersonlist.service.response.PersonPageResponse;
import com.example.mongopersonlist.service.response.PersonResponse;
import com.example.mongopersonlist.util.TestPerson;
import com.example.mongopersonlist.util.TestPersonRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

class PersonServiceImplTest extends BaseTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonDao personDao;

    @Mock
    private ConversionService conversionService;

    private final PersoneToPersoneResponseConverter toPersoneResponseConverter =
            new PersoneToPersoneResponseConverter();
    private final PersonRequestToPersonConverter toPersonConverter =
            new PersonRequestToPersonConverter();

    @Test
    void getAllPaginated_pageNumberAndSize_listReturned() {
        int pageNumber = 1;
        int pageSize = 5;
        List<Person> persons = TestPerson.persons();

        when(personDao.size())
                .thenReturn((long) persons.size());
        when(personDao.getAllPaginated(pageNumber, pageSize))
                .thenReturn(persons);
        when(conversionService.convert(any(Person.class), eq(PersonResponse.class)))
                .thenAnswer((Answer<PersonResponse>) inv -> toPersoneResponseConverter.convert(inv.getArgument(0)));

        PersonPageResponse page = personService.getAllPaginated(pageNumber, pageSize);

        assertNotNull(page);
        assertEquals(persons.size(), page.getTotalElements());
        assertEquals(Math.min(pageSize, persons.size()), page.getPersons().size());
        int indExp = 0;
        int indAct = 0;
        assertAll("Non-equivalent person data",
                () -> assertEquals(persons.get(indExp).getName(), page.getPersons().get(indAct).getName()),
                () -> assertEquals(persons.get(indExp).getBirthDate(), page.getPersons().get(indAct).getBirthDate()),
                () -> assertEquals(persons.get(indExp).getEmail(), page.getPersons().get(indAct).getEmail())
        );
    }

    @Test
    void getById_id_personResponseReturned() {
        String id = UUID.randomUUID().toString();
        Person person = TestPerson.personOne();
        person.setId(id);

        when(personDao.getById(id))
                .thenReturn(person);
        when(conversionService.convert(person, PersonResponse.class))
                .thenAnswer((Answer<PersonResponse>) inv -> toPersoneResponseConverter.convert(inv.getArgument(0)));

        PersonResponse personResponse = personService.getById(id);

        assertNotNull(personResponse);
        assertAll("Non-equivalent person data",
                () -> assertEquals(person.getId(), personResponse.getId()),
                () -> assertEquals(person.getName(), personResponse.getName()),
                () -> assertEquals(person.getBirthDate(), personResponse.getBirthDate()),
                () -> assertEquals(person.getEmail(), personResponse.getEmail()),
                () -> assertIterableEquals(person.getFavoriteBooks(), personResponse.getFavoriteBooks())
        );
    }

    @Test
    void getById_notExistingId_PersonNotFoundExceptionThrown() {
        String id = UUID.randomUUID().toString();

        when(personDao.getById(id))
                .thenReturn(null);

        assertThrows(PersonNotFoundException.class,
                () -> personService.getById(id));
    }

    @Test
    void save_personRequest_savedAndPersonResponseReturned() {
        PersonRequest personRequest = TestPersonRequest.requestOne();
        Person person = TestPerson.fromRequest(personRequest);
        Person personWithId = TestPerson.fromRequest(personRequest);
        String id = UUID.randomUUID().toString();
        personWithId.setId(id);

        when(personDao.getByEmail(person.getEmail()))
                .thenReturn(null);
        when(personDao.save(person))
                .thenReturn(personWithId);
        when(conversionService.convert(personRequest, Person.class))
                .thenAnswer((Answer<Person>) inv -> toPersonConverter.convert(inv.getArgument(0)));
        when(conversionService.convert(personWithId, PersonResponse.class))
                .thenAnswer((Answer<PersonResponse>) inv -> toPersoneResponseConverter.convert(inv.getArgument(0)));

        PersonResponse personResponse = personService.save(personRequest);

        assertNotNull(personResponse);
        assertAll("Non-equivalent person data",
                () -> assertEquals(personWithId.getId(), personResponse.getId()),
                () -> assertEquals(personWithId.getName(), personResponse.getName()),
                () -> assertEquals(personWithId.getBirthDate(), personResponse.getBirthDate()),
                () -> assertEquals(personWithId.getEmail(), personResponse.getEmail()),
                () -> assertIterableEquals(personWithId.getFavoriteBooks(), personResponse.getFavoriteBooks())
        );
    }

    @Test
    void save_personRequestWithAlreadyTakenEmail_EmailAlreadyTakenExceptionThrown() {
        PersonRequest personRequest = TestPersonRequest.requestOne();
        Person person = TestPerson.fromRequest(personRequest);
        Person personWithId = TestPerson.fromRequest(personRequest);
        String id = UUID.randomUUID().toString();
        personWithId.setId(id);

        when(personDao.getByEmail(person.getEmail()))
                .thenReturn(personWithId);

        assertThrows(EmailAlreadyTakenException.class,
                () -> personService.save(personRequest));
    }

    @Test
    void update_idAndPersonRequest_updatedAndPersonResponseReturned() {
        PersonRequest personRequest = TestPersonRequest.requestOne();
        String id = UUID.randomUUID().toString();
        Person somePerson = TestPerson.personOne();
        somePerson.setId(id);
        Person personWithId = TestPerson.fromRequest(personRequest);
        personWithId.setId(id);

        when(personDao.getById(id))
                .thenReturn(somePerson);
        when(personDao.save(personWithId))
                .thenReturn(personWithId);
        when(conversionService.convert(personRequest, Person.class))
                .thenAnswer((Answer<Person>) inv -> toPersonConverter.convert(inv.getArgument(0)));
        when(conversionService.convert(personWithId, PersonResponse.class))
                .thenAnswer((Answer<PersonResponse>) inv -> toPersoneResponseConverter.convert(inv.getArgument(0)));

        PersonResponse personResponse = personService.update(id, personRequest);

        assertNotNull(personResponse);
        assertAll("Non-equivalent somePerson data",
                () -> assertEquals(personWithId.getId(), personResponse.getId()),
                () -> assertEquals(personWithId.getName(), personResponse.getName()),
                () -> assertEquals(personWithId.getBirthDate(), personResponse.getBirthDate()),
                () -> assertEquals(personWithId.getEmail(), personResponse.getEmail()),
                () -> assertIterableEquals(personWithId.getFavoriteBooks(), personResponse.getFavoriteBooks())
        );
    }

    @Test
    void delete_id_personWithIdDeleted() {
        String id = UUID.randomUUID().toString();
        Person person = TestPerson.personOne();
        person.setId(id);

        when(personDao.getById(id))
                .thenReturn(person);
        doAnswer((Answer<Void>) inv -> null)
                .when(personDao).delete(person);

        personService.delete(id);
    }

    @Test
    void delete_notExistingId_PersonNotFoundExceptionThrown() {
        String id = UUID.randomUUID().toString();

        when(personDao.getById(id))
                .thenReturn(null);

        assertThrows(PersonNotFoundException.class,
                () -> personService.delete(id));
    }
}