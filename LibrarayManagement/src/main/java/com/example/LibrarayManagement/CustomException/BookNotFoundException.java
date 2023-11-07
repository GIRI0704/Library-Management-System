package com.example.LibrarayManagement.CustomException;

import com.example.LibrarayManagement.Entity.Book;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message)
    {
        super(message);
    }
}
