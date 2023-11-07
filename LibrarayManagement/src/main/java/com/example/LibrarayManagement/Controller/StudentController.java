package com.example.LibrarayManagement.Controller;

import com.example.LibrarayManagement.DTO.StudentDTO;
import com.example.LibrarayManagement.Entity.Student;
import com.example.LibrarayManagement.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody StudentDTO studentDTO)
    {
        return studentService.addStudent(studentDTO);
    }
}
