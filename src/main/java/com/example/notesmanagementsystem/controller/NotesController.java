package com.example.notesmanagementsystem.controller;

import com.example.notesmanagementsystem.dto.NotesRequestDto;
import com.example.notesmanagementsystem.dto.NotesResponseDto;
import com.example.notesmanagementsystem.service.notesServiceimpl.NotesServiceimpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notes")
public class NotesController {
    private final NotesServiceimpl notesServiceimpl;

    public NotesController(NotesServiceimpl notesServiceimpl) {
        this.notesServiceimpl = notesServiceimpl;
    }

    @PostMapping
    public ResponseEntity<NotesResponseDto> create(@Validated @RequestBody NotesRequestDto notesRequestDto) {
        return new ResponseEntity<>(notesServiceimpl.createNote(notesRequestDto), HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<NotesResponseDto> update(@PathVariable Long id,@Validated @RequestBody NotesRequestDto notesRequestDto) {
        return ResponseEntity.ok(notesServiceimpl.updateNoteById(id, notesRequestDto));
    }
    @GetMapping("{id}")
    public ResponseEntity<NotesResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(notesServiceimpl.getNoteById(id));
    }
    @GetMapping
    public ResponseEntity<Page<NotesResponseDto>> findAllNotes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id")String sortBy) {
        return ResponseEntity.ok(notesServiceimpl.getAllNotes(page,size,sortBy));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<NotesResponseDto>> search(String keyword,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(notesServiceimpl.search(keyword,page,size));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotes(@PathVariable Long id) {
        notesServiceimpl.deleteNoteById(id);
        return new ResponseEntity<>("Note deleted", HttpStatus.OK);
    }
}