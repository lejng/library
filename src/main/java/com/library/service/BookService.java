package com.library.service;

import com.library.dto.BookDto;
import com.library.dto.CreateBookDto;
import com.library.entity.Book;
import com.library.entity.Person;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BookDto save(CreateBookDto bookDto) {
        Book book = new Book(bookDto.name(), bookDto.author());
        repository.save(book);
        return BookDto.from(book);
    }

    public List<BookDto> findAll() {
        return repository.findAll().stream().map(BookDto::from).toList();
    }

    public Optional<BookDto> byId(long id) {
        return repository.findById(id).map(BookDto::from);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void assign(Long bookId, Long personId) {
        repository.findById(bookId).ifPresent(book -> {
            var person = new Person();
            person.setId(personId);
            book.setPerson(person);
        });
    }

    @Transactional
    public void unassign(Long bookId) {
        repository.findById(bookId).ifPresent(book -> book.setPerson(null));
    }

}
