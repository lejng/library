package com.library;

import com.library.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class PersonControllerIntegrationTests extends BaseIntegrationTests {

    @Test
    void checkThatPersonCanBeAdded() {
        PersonDto person = addPerson();
        var response = getRequest("/persons/" + person.id(), PersonDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(person);
    }

    @Test
    void checkThatPersonCanBeDeleted() {
        PersonDto person = addPerson();
        var response = deleteRequest("/persons/" + person.id(), HttpStatus.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personService.byId(person.id())).isNotPresent();
        var responsePersonNotFound = getRequest("/persons/" + person.id(), PersonDto.class);
        assertThat(responsePersonNotFound.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
