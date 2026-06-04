package com.example.notesmanagementsystem.service.notesServiceimpl;

import com.example.notesmanagementsystem.customException.ResourceNotFoundException;
import com.example.notesmanagementsystem.dto.NotesRequestDto;
import com.example.notesmanagementsystem.dto.NotesResponseDto;
import com.example.notesmanagementsystem.entity.Notes;
import com.example.notesmanagementsystem.repository.NotesRepository;
import com.example.notesmanagementsystem.service.NotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NotesServiceimpl implements NotesService {
    private final NotesRepository notesRepository;
    public NotesServiceimpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }
    private static final Logger logger = LoggerFactory.getLogger(NotesServiceimpl.class);

    @Override
    public NotesResponseDto createNote(NotesRequestDto notesRequestDto) {
        logger.info("Creating new note {}", notesRequestDto.getTitle());
        logger.info("Value of content: {}", notesRequestDto.getContent());

        Notes notes = Notes.builder()
                .title(notesRequestDto.getTitle())
                .content(notesRequestDto.getContent())
                .category(notesRequestDto.getCategory())
                .pinned(notesRequestDto.isPinned())
                .build();

        notesRepository.save(notes);
        logger.info("Note created {}", notes.getId());
        return mapToResponseDto(notes);
    }

    @Override
    public NotesResponseDto updateNoteById(Long id, NotesRequestDto notesRequestDto) {
        logger.info("Updating note {}", id);
        Notes notes = notesRepository.findById(id).orElseThrow(()->{
            logger.error("No note found with id {}", id);
            return new ResourceNotFoundException("Notes not found");
        });
        notes.setTitle(notesRequestDto.getTitle());
        notes.setContent(notesRequestDto.getContent());
        notes.setCategory(notesRequestDto.getCategory());
        notes.setPinned(notesRequestDto.isPinned());
        notesRepository.save(notes);
        logger.info("Note updated {}", id);
        return mapToResponseDto(notes);
    }

    @Override
    public NotesResponseDto getNoteById(Long id) {
        logger.info("Getting note {}", id);
        Notes notes = notesRepository.findById(id).orElseThrow(()->{
            logger.error("No note found with id {}", id);
            return new ResourceNotFoundException("Notes not found");
        });
        return mapToResponseDto(notes);
    }

    @Override
    public void deleteNoteById(Long id) {
        logger.info("Deleting note {}", id);
        Notes notes = notesRepository.findById(id).orElseThrow(()->{
            logger.error("No note found with id {}", id);
            return new ResourceNotFoundException("Notes not found");
        });
        notesRepository.delete(notes);
        logger.info("Note deleted {}", id);
    }

    @Override
    public Page<NotesResponseDto> getAllNotes(int page, int size, String sortby) {
        logger.info("Getting all notes page {}size {}", page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortby));
        return notesRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public Page<NotesResponseDto> search(String keyword, int page, int size) {
        logger.info("searching notes with keyword {}", keyword);
        Pageable pageable = PageRequest.of(page, size);
        return notesRepository.findByTitleContainingIgnoreCase(keyword,pageable)
                .map(this::mapToResponseDto);
    }
    
    private NotesResponseDto mapToResponseDto(Notes notes) {
        return new NotesResponseDto(
                notes.getId(),
                notes.getTitle(),
                notes.getContent(),
                notes.getCategory(),
                notes.isPinned(),
                notes.getCreatedAt(),
                notes.getUpdatedAt()
        );
    }
}
