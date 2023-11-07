package com.example.LibrarayManagement.Service;

import com.example.LibrarayManagement.CustomException.AuthorNotFoundException;
import com.example.LibrarayManagement.DTO.AuthorIdDTO;
import com.example.LibrarayManagement.Entity.Author;
import com.example.LibrarayManagement.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepo authorRepo;
    public void addAuthor(AuthorIdDTO authorIdDTO)
    {

        Author author = Author.builder()
                .authorName(authorIdDTO.getName())
                .AuthorRating(authorIdDTO.getRating())
                .age(authorIdDTO.getAge())
                .build();
        authorRepo.save(author);
    }

    public List<String> getAllAuthorName()
    {
        List<Author> authorList = authorRepo.findAll();
        List<String> authorName = new ArrayList<>();

        for(Author author : authorList)
        {
            authorName.add(author.getAuthorName());
        }
        return authorName;
    }

    public AuthorIdDTO getAuthorById(int authorId) throws AuthorNotFoundException
    {
        Optional<Author> optionalAuthor = authorRepo.findById(authorId);
        if(!optionalAuthor.isPresent())
        {
            throw new AuthorNotFoundException("Author Id is inValid");
        }

        Author author = optionalAuthor.get();
        AuthorIdDTO authorIdDTO = AuthorIdDTO.builder()
                .age(author.getAge())
                .name(author.getAuthorName())
                .rating(author.getAuthorRating())
                .build();
        return authorIdDTO;
    }
}
