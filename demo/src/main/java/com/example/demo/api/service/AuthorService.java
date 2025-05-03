package com.example.demo.api.service;

import com.example.demo.api.entity.Author;

import java.util.List;

public interface AuthorService {
    Author AddAuthor(Author author);

    List<Author> getAllAuthor();

    Author DeleteAuthor(Author author);

    Author EditAuthor(Author author);

    Author getAuthorByName(String name);
}
