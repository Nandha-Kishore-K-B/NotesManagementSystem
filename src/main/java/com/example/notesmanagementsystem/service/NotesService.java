package com.example.notesmanagementsystem.service;

import com.example.notesmanagementsystem.dto.NotesRequestDto;
import com.example.notesmanagementsystem.dto.NotesResponseDto;
import org.springframework.data.domain.Page;

public interface NotesService {
    NotesResponseDto createNote(NotesRequestDto notesRequestDto);
    NotesResponseDto updateNoteById(Long id, NotesRequestDto notesRequestDto);
    NotesResponseDto getNoteById(Long id);
    void deleteNoteById(Long id);
    Page<NotesResponseDto> getAllNotes(int page, int size,String sortby);
    Page<NotesResponseDto> search(String keyword,int page,int size);
}
