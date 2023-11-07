package com.example.LibrarayManagement.CustomException;

public class LibraryCardNotFoundException extends Exception{
    public LibraryCardNotFoundException(String message)
    {
        super(message);
    }
}
