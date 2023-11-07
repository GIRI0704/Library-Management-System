package com.example.LibrarayManagement.Service;

import com.example.LibrarayManagement.CustomException.LibraryCardNotFoundException;
import com.example.LibrarayManagement.CustomException.StudentNotFountdException;
import com.example.LibrarayManagement.Entity.LibraryCard;
import com.example.LibrarayManagement.Entity.Student;
import com.example.LibrarayManagement.Enum.CardStatus;
import com.example.LibrarayManagement.Repository.LibraryCardRepo;
import com.example.LibrarayManagement.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryCardService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    LibraryCardRepo libraryCardRepo;
    @Autowired
    StudentRepo studentRepo;

    public String generateNewCard()
    {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardStatus(CardStatus.NEW);
        libraryCardRepo.save(libraryCard);
        return "New Library Card add into DB";
    }

    public String associateCardWithStudent(int studentId, int libraryCardId) throws Exception
    {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        Optional<LibraryCard> optionalLibraryCard = libraryCardRepo.findById(libraryCardId);

        if(!optionalStudent.isPresent())
        {
            throw new StudentNotFountdException("Student ID is inValid");
        }
        if(!optionalLibraryCard.isPresent())
        {
            throw new LibraryCardNotFoundException("LibraryCard ID is inValid");
        }

        Student student = optionalStudent.get();
        LibraryCard libraryCard = optionalLibraryCard.get();

        if(student.getLibraryCard()!=null) return "Student already associate with LibraryCard Id "+student.getLibraryCard().getCardId();
        if(libraryCard.getStudent()!= null) return "LibraryCard already associate with Student Id "+libraryCard.getStudent().getStudentId();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOfCard(student.getStudentName());
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);

        studentRepo.save(student);

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        String body ="Hi "+student.getStudentName()+" you are added to the Library and your Card Id = "+libraryCard.getCardId();
        mailMessage.setFrom("springproject474@gmail.com");
        mailMessage.setTo(student.getEmail());
        mailMessage.setSubject("Welcome to Library");
        mailMessage.setText(body);
        mailSender.send(mailMessage);

        return "Student Id "+studentId+" is succesfully associated with LibraryCard Id "+libraryCardId;
    }
}
