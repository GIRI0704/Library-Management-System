package com.example.LibrarayManagement.Repository;

import com.example.LibrarayManagement.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepo extends JpaRepository<LibraryCard,Integer> {
}
