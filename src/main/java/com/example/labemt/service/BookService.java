package com.example.labemt.service;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.dto.BookDto;
import com.example.labemt.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, BookDto bookDto);
    void delete(Long id);
    Optional<Book> markAsTaken(Long id);
}
