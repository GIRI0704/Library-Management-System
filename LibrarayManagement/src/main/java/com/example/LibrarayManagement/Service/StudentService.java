package com.example.LibrarayManagement.Service;

import com.example.LibrarayManagement.DTO.StudentDTO;
import com.example.LibrarayManagement.Entity.Student;
import com.example.LibrarayManagement.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    public String addStudent(StudentDTO studentDTO)
    {
        Student student = Student.builder()
                .studentName(studentDTO.getStudentName())
                .age(studentDTO.getAge())
                .email(studentDTO.getEmail())
                .mobile(studentDTO.getMobile())
                .build();
        studentRepo.save(student);
        return "Student added";
    }
}
