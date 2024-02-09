package com.example.jenkins_e2.controller;

import com.example.jenkins_e2.model.Book;
import com.example.jenkins_e2.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {
    private final LibraryService libraryService;

    LibraryController(final LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/book/{isbn}")
    ResponseEntity<Book> getBook(@PathVariable String isbn) {
        Book resBook = libraryService.getBookByIsbn(isbn);
        return new ResponseEntity<>(resBook, resBook == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PutMapping("/book")
    ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book resBook = libraryService.storeBook(book);
        return new ResponseEntity<>(resBook, HttpStatus.OK);
    }
}
