package com.example.LibrarayManagement.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorIdDTO {
    private String name;
    private int age;
    private double rating;
}
