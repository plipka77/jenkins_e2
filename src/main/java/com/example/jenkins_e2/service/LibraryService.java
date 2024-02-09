package com.example.jenkins_e2.service;

import com.example.jenkins_e2.model.Book;
import com.example.jenkins_e2.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;

    public Book getBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);
        return optionalBook.orElse(null);
    }

    public Book storeBook(Book newBook) {
        return bookRepository.save(newBook);
    }
}
