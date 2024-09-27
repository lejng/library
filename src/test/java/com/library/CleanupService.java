package com.library;

import com.library.repository.BookRepository;
import com.library.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class CleanupService {

    private final BookRepository bookRepository;

    private final PersonRepository PersonRepository;

    public CleanupService(BookRepository bookRepository, PersonRepository PersonRepository) {
        this.bookRepository = bookRepository;
        this.PersonRepository = PersonRepository;
    }

    public void cleanDB() {
        bookRepository.deleteAll();
        PersonRepository.deleteAll();
    }
}
