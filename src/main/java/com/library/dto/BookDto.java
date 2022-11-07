package com.library.dto;

import com.library.entity.Book;
import com.library.entity.Person;

import java.util.Optional;

public record BookDto(long id, String name, String author, PersonInfo person) {

    public static BookDto from(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor(), PersonInfo.from(book.getPerson()));
    }

    public record PersonInfo(long id, String name, String surname) {
        static PersonInfo from(Person person) {
            return Optional.ofNullable(person)
                    .map(p -> new PersonInfo(p.getId(), p.getName(), p.getSurname()))
                    .orElse(null);
        }
    }

}
