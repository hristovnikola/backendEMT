package com.example.labemt.service.impl;

import com.example.labemt.model.Author;
import com.example.labemt.model.Country;
import com.example.labemt.model.dto.AuthorDto;
import com.example.labemt.model.exceptions.AuthorNotFoundException;
import com.example.labemt.model.exceptions.CountryNotFoundException;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());

        Country country = this.countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        this.authorRepository.delete(author);
    }
}
