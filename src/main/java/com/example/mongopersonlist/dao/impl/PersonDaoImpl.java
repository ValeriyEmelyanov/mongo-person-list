package com.example.mongopersonlist.dao.impl;

import com.example.mongopersonlist.dao.PersonDao;
import com.example.mongopersonlist.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Person save(Person person) {
        mongoTemplate.save(person);
        return person;
    }

    @Override
    public List<Person> getAll() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public List<Person> getAllPaginated(int pageNumber, int pageSize) {
        Query query = new Query();
        query.skip((long) pageNumber * pageSize);
        query.limit(pageSize);
        return mongoTemplate.find(query, Person.class);
    }

    @Override
    public Person findOneByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Person.class);
    }

    @Override
    public List<Person> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Person.class);
    }

    @Override
    public List<Person> findByBirthDateAfter(LocalDate date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("birthDate").gt(date));
        return mongoTemplate.find(query, Person.class);
    }

    @Override
    public Person update(Person person) {
        mongoTemplate.save(person);
        return person;
    }

    @Override
    public void delete(Person person) {
        mongoTemplate.remove(person);
    }

    @Override
    public void deleteAll() {
        if (mongoTemplate.collectionExists(Person.class)) {
            mongoTemplate.dropCollection(Person.class);
        }
    }
}
