package com.example.notesmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotesRequestDto {
    @NotBlank(message = "Title shouldn't be blank")
    private String title;
    @NotBlank(message = "content shouldn't be blank")
    @Size(min = 5,message = "minimum 5 characters")
    @Size(max = 2000,message = "maximum 2000 characters")
    private String content;
    private String category;
    private boolean pinned;

}
