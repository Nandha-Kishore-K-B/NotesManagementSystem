package com.example.notesmanagementsystem.repository;

import com.example.notesmanagementsystem.entity.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
    Page<Notes> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
