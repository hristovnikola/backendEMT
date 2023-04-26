package com.example.labemt.service.impl;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.dto.BookDto;
import com.example.labemt.model.enumerations.Category;
import com.example.labemt.model.exceptions.AuthorNotFoundException;
import com.example.labemt.model.exceptions.BookNotFoundException;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.BookRepository;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());

        Author author = authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(id));;
        book.setAuthor(author);

        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        this.bookRepository.delete(book);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer availableCopies = book.getAvailableCopies();

        if(availableCopies >= 1)
            book.setAvailableCopies(availableCopies - 1);

        this.bookRepository.save(book);
        return Optional.of(book);
    }
}
