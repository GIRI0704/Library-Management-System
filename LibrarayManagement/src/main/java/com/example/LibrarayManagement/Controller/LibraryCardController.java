package com.example.LibrarayManagement.Controller;

import com.example.LibrarayManagement.Service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LibraryCard")
public class LibraryCardController {
    @Autowired
    LibraryCardService libraryCardService;

    @PostMapping("generateNewCard")
    public String generateNewCard()
    {
        return libraryCardService.generateNewCard();
    }

    @PutMapping("associateCardWithStudent")
    public String associateCardWithStudent(@RequestParam("studentId") int studentId, @RequestParam("libraryCardId") int libraryCardId)
    {
        try {
            return libraryCardService.associateCardWithStudent(studentId,libraryCardId);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

}
