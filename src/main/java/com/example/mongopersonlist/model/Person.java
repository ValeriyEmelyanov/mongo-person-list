package com.example.mongopersonlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private List<String> favoriteBooks;

    public Person(String name, List<String> favoriteBooks) {
        this.name = name;
        this.favoriteBooks = favoriteBooks;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", favoriteBooks=" + favoriteBooks +
                '}';
    }
}
