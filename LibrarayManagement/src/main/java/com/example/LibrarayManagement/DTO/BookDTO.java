package com.example.LibrarayManagement.DTO;

import com.example.LibrarayManagement.Enum.Genre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String bookName;
    private int price;
    private int noOfPages;
    private Genre genre;
    private double bookRating;
    private boolean isAvailable;
    private String authorName;
}
