package com.library.controller;

import com.library.dto.BookDto;
import com.library.dto.CreateBookDto;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> showBooks(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(bookService.find(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> showBook(@PathVariable("id") Long id) {
        return bookService.byId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody CreateBookDto bookDto) {
        // add validation
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        // can return status
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{bookId}/assign/{personId}")
    public ResponseEntity<HttpStatus> assign(@PathVariable("bookId") Long bookId, @PathVariable("personId") Long personId) {
        bookService.assign(bookId, personId);
        // can return status, add validation
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{bookId}/unassign")
    public ResponseEntity<HttpStatus> unassign(@PathVariable("bookId") Long bookId) {
        bookService.unassign(bookId);
        // can return status, add validation
        return ResponseEntity.ok().build();
    }

}
