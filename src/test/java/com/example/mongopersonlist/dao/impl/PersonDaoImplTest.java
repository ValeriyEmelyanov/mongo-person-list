package com.example.mongopersonlist.dao.impl;

import com.example.mongopersonlist.model.Person;
import com.example.mongopersonlist.util.TestPerson;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PersonDaoImplTest {

    @Autowired
    private PersonDaoImpl personDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void tearDown() {
        mongoTemplate.getCollection("person").deleteMany(new Document());
    }

    @Test
    void save_person_savedAndPersonReturned() {
        Person person = TestPerson.personOne();

        Person saved = personDao.save(person);

        assertNotNull(saved.getId());
        assertEquals(1, mongoTemplate.getCollection("person").countDocuments());
    }

    @Test
    void getAllPaginated_pageNumberAndSize_listReturned() {
        Person person = TestPerson.personOne();
        mongoTemplate.save(person);
        Person person2 = TestPerson.personTwo();
        mongoTemplate.save(person2);

        List<Person> persons = personDao.getAllPaginated(1, 2);

        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    void getById_id_personReturned() {
        Person person = TestPerson.personOne();
        mongoTemplate.save(person);
        Person person2 = TestPerson.personTwo();
        mongoTemplate.save(person2);

        Person fetched = personDao.getById(person.getId());

        assertNotNull(fetched);
        assertEquals(person.getId(), fetched.getId());
    }

    @Test
    void size_personCollectonSizeReturned() {
        Person person = TestPerson.personOne();
        mongoTemplate.save(person);
        Person person2 = TestPerson.personTwo();
        mongoTemplate.save(person2);

        long size = personDao.size();

        assertEquals(2, size);
    }

    @Test
    void delete_person_personDeleted() {
        Person person = TestPerson.personOne();
        mongoTemplate.save(person);
        Person person2 = TestPerson.personTwo();
        mongoTemplate.save(person2);

        personDao.delete(person);

        assertEquals(1, mongoTemplate.getCollection("person").countDocuments());

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(person.getId()));
        Person fetched = mongoTemplate.findOne(query, Person.class);
        assertNull(fetched);
    }
}