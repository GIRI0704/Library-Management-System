package com.example.LibrarayManagement.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //This means it's a structure that will be reflected in Database
@Table(name = "Student") //This class will have a table whose name is studentTable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;
    private int age;
    private String mobile;
    private String email;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}
