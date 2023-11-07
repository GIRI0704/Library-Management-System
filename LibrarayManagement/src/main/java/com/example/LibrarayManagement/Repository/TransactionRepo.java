package com.example.LibrarayManagement.Repository;

import com.example.LibrarayManagement.Entity.Book;
import com.example.LibrarayManagement.Entity.LibraryCard;
import com.example.LibrarayManagement.Entity.Transaction;
import com.example.LibrarayManagement.Enum.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.book.bookId = :bookId AND t.libraryCard.cardId = :libraryCardId AND t.transactionStatus = :transactionStatus")
    Optional<Transaction> findTransactionByBookAndLibraryCard(
            @Param("bookId") Integer bookId,
            @Param("libraryCardId") Integer libraryCardId,
            @Param("transactionStatus") TransactionStatus transactionStatus
    );
}
