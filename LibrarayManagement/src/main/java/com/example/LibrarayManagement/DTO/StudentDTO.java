package com.example.LibrarayManagement.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String studentName;
    private int age;
    private String mobile;
    private String email;
}
