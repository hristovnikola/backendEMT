package com.example.labemt.web.rest;


import com.example.labemt.model.Author;
import com.example.labemt.model.enumerations.Category;
import com.example.labemt.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping()
    public List<Author> listAllAuthors() {
        return this.authorService.listAll();
    }
}