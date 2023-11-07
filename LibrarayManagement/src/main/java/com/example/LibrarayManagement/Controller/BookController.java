package com.example.LibrarayManagement.Controller;

import com.example.LibrarayManagement.DTO.AddBookDTO;
import com.example.LibrarayManagement.DTO.BookDTO;
import com.example.LibrarayManagement.Entity.Book;
import com.example.LibrarayManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody AddBookDTO addBookDTO, @RequestParam("authorId") int authorId) {
        try {
            String result = bookService.addBook(addBookDTO, authorId);
            return new ResponseEntity(result, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookGenre")
    public ResponseEntity getBookGenre(@RequestParam("genre") String genre) {
        try {
            List<BookDTO> bookDTOList = bookService.getBookGenre(genre);
            return new ResponseEntity<>(bookDTOList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
        }
    }

    @GetMapping("showAllBooks")
    public ResponseEntity showAllBooks() {
        try {
            List<String> books = bookService.showAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getBookByAuthor")
    public ResponseEntity getBookByAuthor(@RequestParam("Author") String name)
    {
        try
        {
            List<BookDTO> bookDTOList = bookService.getBookByAuthor(name);
            return new ResponseEntity<>(bookDTOList,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
