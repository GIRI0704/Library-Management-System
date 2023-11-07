package com.example.LibrarayManagement.Service;

import com.example.LibrarayManagement.CustomException.BookNotFoundException;
import com.example.LibrarayManagement.CustomException.LibraryCardNotFoundException;
import com.example.LibrarayManagement.Entity.Book;
import com.example.LibrarayManagement.Entity.LibraryCard;
import com.example.LibrarayManagement.Entity.Transaction;
import com.example.LibrarayManagement.Enum.CardStatus;
import com.example.LibrarayManagement.Enum.TransactionStatus;
import com.example.LibrarayManagement.Repository.BookRepo;
import com.example.LibrarayManagement.Repository.LibraryCardRepo;
import com.example.LibrarayManagement.Repository.TransactionRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    LibraryCardRepo libraryCardRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    TransactionRepo transactionRepo;

    private static final int maxBookLimit = 5;
    private static final int finePerDay = 3;
    public String issueBook(int bookId, int cardId) throws Exception
    {
        Optional<LibraryCard> optionalLibraryCard = libraryCardRepo.findById(cardId);

        if(!optionalLibraryCard.isPresent())
        {
            throw  new LibraryCardNotFoundException("Library Card Id is inValid");
        }

        Optional<Book> optionalBook = bookRepo.findById(bookId);

        if(!optionalBook.isPresent())
        {
            throw  new BookNotFoundException("Book Id is inValid");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();
        Book book = optionalBook.get();

        if(!book.isAvailable())
        {
            throw new BookNotFoundException("Book is currently unavailabe");
        }

        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE))
        {
            throw new LibraryCardNotFoundException("LibraryCard is not in Active");
        }

        if(libraryCard.getNoOfBookIssued() == maxBookLimit)
        {
            throw new Exception("you have reached maximum limit of books which is "+maxBookLimit+" books");
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setAvailable(false);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued()+1);

        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        transactionRepo.save(transaction);
        return "The book with bookId "+bookId+" has been issued " + " to card with "+cardId;
    }

    public String returnBook(int bookId, int cardId) throws Exception
    {
        Optional<LibraryCard> optionalLibraryCard = libraryCardRepo.findById(cardId);

        if(!optionalLibraryCard.isPresent())
        {
            throw  new LibraryCardNotFoundException("Library Card Id is inValid");
        }

        Optional<Book> optionalBook = bookRepo.findById(bookId);

        if(!optionalBook.isPresent())
        {
            throw  new BookNotFoundException("Book Id is inValid");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();
        Book book = optionalBook.get();

        Transaction transaction = transactionRepo.findTransactionByBookAndLibraryCard(bookId,cardId,TransactionStatus.ISSUED).get();
        Date issueDate = transaction.getCreatedOn();

        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSeconds,TimeUnit.MILLISECONDS);

        int fineAmount = 0;
        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * finePerDay);
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        newTransaction.setBook(book);
        newTransaction.setLibraryCard(libraryCard);

        book.setAvailable(true);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued()-1);

        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        transactionRepo.save(newTransaction);
        return "The book with bookId "+bookId+" has been returned " + " by card with "+cardId;
    }
}
