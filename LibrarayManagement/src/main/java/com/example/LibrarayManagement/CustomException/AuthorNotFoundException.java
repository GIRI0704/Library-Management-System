package com.example.LibrarayManagement.CustomException;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message)
    {
        super(message);
    }
}
