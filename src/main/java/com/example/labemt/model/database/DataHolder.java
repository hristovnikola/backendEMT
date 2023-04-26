package com.example.labemt.model.database;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.Country;
import com.example.labemt.model.enumerations.Category;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.BookRepository;
import com.example.labemt.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        Country usa = new Country("USA", "North America");
        Country mk = new Country("Macedonia", "Europe");
        Country fr = new Country("France", "Europe");
        Country ar = new Country("Argentina", "South America");
        Country sm = new Country("Somalia", "Africa");

        countryRepository.save(usa);
        countryRepository.save(mk);
        countryRepository.save(fr);
        countryRepository.save(ar);
        countryRepository.save(sm);

        Author author1 = new Author("Nikola", "Hristov", usa);
        Author author2 = new Author("Petar", "Solakovski", mk);
        Author author3 = new Author("Jovan", "Talevski", mk);
        Author author4 = new Author("Filip", "Markovski", mk);

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);

        Book book1 = new Book("Book 1", Category.BIOGRAPHY, author1, 10);
        Book book2 = new Book("Book 2", Category.DRAMA, author2, 5);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}

