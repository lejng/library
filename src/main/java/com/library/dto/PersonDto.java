package com.library.dto;


import com.library.entity.Book;
import com.library.entity.Person;

import java.util.List;

public record PersonDto(long id, String name, String surname, List<BookInfo> books) {

    public static PersonDto from(Person person) {
        return new PersonDto(
                person.getId(),
                person.getName(),
                person.getSurname(),
                person.getBooks().stream().map(BookInfo::from).toList()
        );
    }

    public record BookInfo(long id, String name, String author) {
        static BookInfo from(Book book) {
            return new BookInfo(book.getId(), book.getName(), book.getAuthor());
        }
    }

}
