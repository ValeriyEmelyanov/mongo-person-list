package com.example.mongopersonlist;

import com.example.mongopersonlist.dao.PersonDao;
import com.example.mongopersonlist.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MongoPersonListApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MongoPersonListApplication.class);

    private final PersonDao personDao;
    private final ConfigurableApplicationContext context;

    @Autowired
    public MongoPersonListApplication(PersonDao personDao, ConfigurableApplicationContext context) {
        this.personDao = personDao;
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoPersonListApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        personDao.deleteAll();

        personDao.save(new Person("Shubham", Arrays.asList("Harry potter", "Waking Up")));
        personDao.save(new Person("Sergey", Arrays.asList("Startup Guides", "Java")));
        personDao.save(new Person("David", Arrays.asList("Harry potter", "Success")));
        personDao.save(new Person("Ivan", Arrays.asList("Secrets of Butene", "Meeting Success")));
        personDao.save(new Person("Sergey", Arrays.asList("Harry potter", "Startup Guides")));

        System.out.printf("Getting all data from MongoDB: \n%s\n",
                personDao.getAll());
        System.out.printf("Getting paginated data from MongoDB: \n%s\n",
                personDao.getAllPaginated(0, 2));
        System.out.printf("Getting person By name 'Sergey': \n%s\n",
                personDao.findOneByName("Sergey"));
        System.out.printf("Getting all person By name 'Sergey': \n%s\n",
                personDao.findByName("Sergey"));

        context.close();
    }
}
