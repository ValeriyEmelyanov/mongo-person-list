package com.example.mongopersonlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * Describes Person entity/collection.
 */
@Document(collection = "person")
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    @Indexed(unique = true)
    private String email;
    private List<String> favoriteBooks;

    public Person(String name, LocalDate birthDate, String email, List<String> favoriteBooks) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.favoriteBooks = favoriteBooks;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", favoriteBooks=" + favoriteBooks +
                '}';
    }
}
