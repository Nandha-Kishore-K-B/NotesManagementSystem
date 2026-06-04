package com.example.notesmanagementsystem.controller;

import com.example.notesmanagementsystem.dto.NotesRequestDto;
import com.example.notesmanagementsystem.dto.NotesResponseDto;
import com.example.notesmanagementsystem.service.notesServiceimpl.NotesServiceimpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes Management", description = "Endpoints for creating, updating, retrieving, and deleting notes")

public class NotesController {
    private final NotesServiceimpl notesServiceimpl;

    public NotesController(NotesServiceimpl notesServiceimpl) {
        this.notesServiceimpl = notesServiceimpl;
    }
    @Operation(summary = "Create a new note", description = "Saves a new note to the database. Title and content cannot be blank.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Note successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input (Validation failed)")
    })
    @PostMapping
    public ResponseEntity<NotesResponseDto> create(@Validated @RequestBody NotesRequestDto notesRequestDto) {
        return new ResponseEntity<>(notesServiceimpl.createNote(notesRequestDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Get a note by ID", description = "Fetches a single note from the database based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note found"),
            @ApiResponse(responseCode = "404", description = "Note not found with the provided ID")
    })
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