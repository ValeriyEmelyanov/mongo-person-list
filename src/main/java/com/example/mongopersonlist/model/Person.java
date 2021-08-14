package com.example.mongopersonlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(collection = "person")
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    private List<String> favoriteBooks;

    public Person(String name, LocalDate birthDate, List<String> favoriteBooks) {
        this.name = name;
        this.birthDate = birthDate;
        this.favoriteBooks = favoriteBooks;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", favoriteBooks=" + favoriteBooks +
                '}';
    }
}
