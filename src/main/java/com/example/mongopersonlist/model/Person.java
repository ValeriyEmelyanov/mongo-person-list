package com.example.mongopersonlist.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@ToString
@EqualsAndHashCode(of = {"id"}, doNotUseGetters = true)
public class Person {
    @Id
    private String id;
    private String name;
    private LocalDate birthDate;
    @Indexed(unique = true)
    private String email;
    private List<String> favoriteBooks;
}
