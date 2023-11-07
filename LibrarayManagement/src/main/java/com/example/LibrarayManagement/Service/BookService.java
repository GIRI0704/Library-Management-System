package com.example.LibrarayManagement.Service;

import com.example.LibrarayManagement.CustomException.AuthorNotFoundException;
import com.example.LibrarayManagement.CustomException.BookNotFoundException;
import com.example.LibrarayManagement.DTO.AddBookDTO;
import com.example.LibrarayManagement.DTO.BookDTO;
import com.example.LibrarayManagement.Entity.Author;
import com.example.LibrarayManagement.Entity.Book;
import com.example.LibrarayManagement.Repository.AuthorRepo;
import com.example.LibrarayManagement.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;
    @Autowired
    AuthorRepo authorRepo;

    public String addBook(AddBookDTO addBookDTO, int authorId) throws AuthorNotFoundException
    {
        Optional<Author> optionalAuthor = authorRepo.findById(authorId);
        if(!optionalAuthor.isPresent())
        {
            throw new AuthorNotFoundException("Author Id is InValid");
        }
        Author author = optionalAuthor.get();
        Book book = Book.builder()
                .bookRating(addBookDTO.getBookRating())
                .bookName(addBookDTO.getBookName())
                .price(addBookDTO.getPrice())
                .noOfPages(addBookDTO.getNoOfPages())
                .genre(addBookDTO.getGenre())
                .isAvailable(addBookDTO.isAvailable())
                .build();
        book.setAuthor(author);
        author.getBookList().add(book);
        authorRepo.save(author);
        return "Book added in DB";
    }

    public List<BookDTO> getBookGenre(String genre) throws BookNotFoundException
    {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<Book> bookList = bookRepo.findAll();

        for(Book book : bookList)
        {
            if(book.getGenre().name().equals(genre))
            {
                BookDTO bookDTO = BookDTO.builder().
                        bookName(book.getBookName()).
                        noOfPages(book.getNoOfPages()).
                        bookRating(book.getBookRating()).
                        price(book.getPrice()).
                        genre(book.getGenre()).
                        isAvailable(book.isAvailable()).
                        authorName(book.getAuthor().getAuthorName()).
                        build();
                bookDTOList.add(bookDTO);
            }
        }

        if(bookDTOList.size() == 0)
        {
            throw new BookNotFoundException("Book is not Available");
        }
        return bookDTOList;
    }

    public List<String> showAllBooks() throws BookNotFoundException
    {
        List<Book> books=bookRepo.findAll();
        if(books.isEmpty()){
            throw new BookNotFoundException("Books Not Available");
        }
        List<String> allbooks=new ArrayList<>();
        for(Book book:books){
            allbooks.add("Book ID = "+book.getBookId()+" ---> Book Name = : "+book.getBookName()+" Author Name : "+book.getAuthor().getAuthorName()+" Genre : "+book.getGenre());
        }
        return allbooks;
    }

    public List<BookDTO> getBookByAuthor(String name) throws AuthorNotFoundException
    {
        List<BookDTO> bookDTOList = new ArrayList<>();
        List<Book> bookList = bookRepo.findAll();

        for (Book book : bookList) {
            if (book.getAuthor().getAuthorName().equals(name)) {
                BookDTO bookDTO = BookDTO.builder().
                        bookName(book.getBookName()).
                        noOfPages(book.getNoOfPages()).
                        bookRating(book.getBookRating()).
                        price(book.getPrice()).
                        genre(book.getGenre()).
                        isAvailable(book.isAvailable()).
                        authorName(book.getAuthor().getAuthorName()).
                        build();
                bookDTOList.add(bookDTO);
            }
        }
        if(bookDTOList.size() == 0)
        {
            throw new AuthorNotFoundException("Author is not Available");
        }
        return bookDTOList;
    }
}
