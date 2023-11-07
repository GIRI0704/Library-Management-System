package com.example.LibrarayManagement.DTO;

import com.example.LibrarayManagement.Enum.Genre;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookDTO {
    private String bookName;
    private int price;
    private int noOfPages;
    private Genre genre;
    private double bookRating;
    private boolean isAvailable;
    private String authorName;
}
