package com.example.LibrarayManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String authorName;
    private int age;
    private double AuthorRating;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> bookList = new ArrayList<>();
}
