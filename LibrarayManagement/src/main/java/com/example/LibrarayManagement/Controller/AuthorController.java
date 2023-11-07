package com.example.LibrarayManagement.Controller;

import com.example.LibrarayManagement.DTO.AuthorIdDTO;
import com.example.LibrarayManagement.Entity.Author;
import com.example.LibrarayManagement.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorIdDTO authorIdDTO)
    {
        authorService.addAuthor(authorIdDTO);
        return new ResponseEntity<>("Author added successfull", HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllAuthor")
    public List<String> getAllAuthorName()
    {
        return authorService.getAllAuthorName();
    }

    @GetMapping("/getAuthor/{authorId}")
    public ResponseEntity getAuthorById(@PathVariable("authorId") int authorId)
    {
        try
        {
            AuthorIdDTO authorIdDTO = authorService.getAuthorById(authorId);
            return new ResponseEntity<>(authorIdDTO,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
