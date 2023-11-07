package com.example.LibrarayManagement.Controller;

import com.example.LibrarayManagement.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public ResponseEntity issueBook(@RequestParam("bookId") int bookId, @RequestParam("cardId") int cardId)
    {
        try {
            String result = transactionService.issueBook(bookId,cardId);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/returnBook")
    public ResponseEntity returnBook(@RequestParam("bookId") int bookId, @RequestParam("cardId") int cardId)
    {
        try {
            String result = transactionService.returnBook(bookId,cardId);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
