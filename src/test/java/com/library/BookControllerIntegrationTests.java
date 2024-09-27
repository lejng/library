package com.library;

import com.library.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class BookControllerIntegrationTests extends BaseIntegrationTests {

    @Test
    void checkThatBookCanBeAssign() {
        var book = addBook();
        var person = addPerson();

        var path = String.format("/books/%s/assign/%s", book.id(), person.id());
        var response = patchRequest(path, HttpStatus.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        var bookActualResponse = getRequest("/books/" + book.id(), BookDto.class);
        assertThat(bookActualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        var bookActual = bookActualResponse.getBody();
        assertThat(bookActual.id()).isEqualTo(book.id());
        assertThat(bookActual.name()).isEqualTo(book.name());
        assertThat(bookActual.author()).isEqualTo(book.author());
        assertThat(bookActual.person().id()).isEqualTo(person.id());
        assertThat(bookActual.person().name()).isEqualTo(person.name());
        assertThat(bookActual.person().surname()).isEqualTo(person.surname());
    }

    @Test
    void checkThatBookCanBeUnassign() {
        var book = addBook();
        var person = addPerson();
        bookService.assign(book.id(), person.id());
        var bookTaken = getRequest("/books/" + book.id(), BookDto.class).getBody();
        assertThat(bookTaken.person().id()).isEqualTo(person.id());

        var path = String.format("/books/%s/unassign", book.id(), person.id());
        var response = patchRequest(path, HttpStatus.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        var bookActualResponse = getRequest("/books/" + book.id(), BookDto.class);
        assertThat(bookActualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        var bookActual = bookActualResponse.getBody();
        assertThat(bookActual.id()).isEqualTo(book.id());
        assertThat(bookActual.name()).isEqualTo(book.name());
        assertThat(bookActual.author()).isEqualTo(book.author());
        assertThat(bookActual.person()).isNull();
    }

    @Test
    void checkThatBookCanBeAdded() {
        var book = addBook();
        var response = getRequest("/books/" + book.id(), BookDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(book);
    }

    @Test
    void checkThatBookCanBeDeleted() {
        var book = addBook();
        var response = deleteRequest("/books/" + book.id(), HttpStatus.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(bookService.byId(book.id())).as("Book must be deleted").isNotPresent();
        var responseBookNotFound = getRequest("/books/" + book.id(), BookDto.class);
        assertThat(responseBookNotFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
