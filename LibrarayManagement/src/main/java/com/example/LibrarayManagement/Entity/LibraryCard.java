package com.example.LibrarayManagement.Entity;

import com.example.LibrarayManagement.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LibraryCard")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    private String nameOfCard;
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    private int noOfBookIssued;

    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
