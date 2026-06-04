package com.example.notesmanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotesResponseDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private boolean pinned;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NotesResponseDto(Long id,String title, String content, String category, boolean pinned, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.pinned = pinned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
