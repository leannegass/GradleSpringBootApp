package org.leanne.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.leanne.model.BookModel;
import org.leanne.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<List<BookModel>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<BookModel> books = new ArrayList<BookModel>();

            if (title == null)
                books.addAll(bookRepository.findAll());
            else
                books.addAll(bookRepository.findByTitleContaining(title));

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable("id") long id) {
        Optional<BookModel> bookData = bookRepository.findById(id);

        return bookData.map(bookModel -> new ResponseEntity<>(bookModel, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books")
    public ResponseEntity<BookModel> createBook(@RequestBody BookModel book) {
        try {
            BookModel _book = bookRepository
                    .save(new BookModel(book.getTitle()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}










