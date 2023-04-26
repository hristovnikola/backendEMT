package com.example.labemt.model.dto;

import com.example.labemt.model.Author;
import com.example.labemt.model.enumerations.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;
}
