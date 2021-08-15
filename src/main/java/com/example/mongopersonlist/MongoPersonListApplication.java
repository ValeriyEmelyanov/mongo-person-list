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

import java.time.LocalDate;
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

        personDao.save(new Person("Shubham",
                LocalDate.of(2004, 2, 21),
                "shubham@gmail.com",
                Arrays.asList("Harry potter", "Waking Up")));
        personDao.save(new Person("Sergey",
                LocalDate.of(1978, 9, 1),
                "sergey@gmail.com",
                Arrays.asList("Startup Guides", "Java")));
        personDao.save(new Person("David",
                LocalDate.of(1991, 5, 29),
                "david@gmail.com",
                Arrays.asList("Harry potter", "Success")));
        personDao.save(new Person("Ivan",
                LocalDate.of(2001, 7, 12),
                "ivan@mail.com",
                Arrays.asList("Secrets of Butene", "Meeting Success")));
        personDao.save(new Person("Sergey",
                LocalDate.of(1998, 3, 28),
                "serg@gmail.com",
                Arrays.asList("Harry potter", "Startup Guides")));

        System.out.printf("Getting all data from MongoDB: \n%s\n",
                personDao.getAll());
        System.out.printf("Getting paginated data from MongoDB: \n%s\n",
                personDao.getAllPaginated(0, 2));
        System.out.printf("Getting person By name 'Sergey': \n%s\n",
                personDao.findOneByName("Sergey"));
        System.out.printf("Getting all person By name 'Sergey': \n%s\n",
                personDao.findByName("Sergey"));
        System.out.printf("Getting all person with birth date after 2000-01-01: \n%s\n",
                personDao.findByBirthDateAfter(LocalDate.of(2000, 1, 1)));
        System.out.printf("Getting all person with the favorite book \"Harry potter\": \n%s\n",
                personDao.findByFavoriteBook("Harry potter"));

        context.close();
    }
}
