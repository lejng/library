package com.library;

import com.library.dto.BookDto;
import com.library.dto.CreateBookDto;
import com.library.dto.CreatePersonDto;
import com.library.dto.PersonDto;
import com.library.service.BookService;
import com.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class BaseIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	protected PersonService personService;

	@Autowired
	protected BookService bookService;

	protected PersonDto addPerson() {
		var expectedPerson = new CreatePersonDto("Dick", "Pick");
		var response = postRequest("/persons", expectedPerson, PersonDto.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		var actualPerson = response.getBody();
		assertThat(actualPerson).isNotNull();
		assertThat(actualPerson.name()).isEqualTo(expectedPerson.name());
		assertThat(actualPerson.surname()).isEqualTo(expectedPerson.surname());
		assertThat(actualPerson.books()).isEmpty();
		return actualPerson;
	}

	protected BookDto addBook() {
		var expectedBook = new CreateBookDto("name 1", "author 1");
		var response = postRequest("/books", expectedBook, BookDto.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		var actualBook = response.getBody();
		assertThat(actualBook).isNotNull();
		assertThat(actualBook.author()).isEqualTo(expectedBook.author());
		assertThat(actualBook.name()).isEqualTo(expectedBook.name());
		assertThat(actualBook.person()).isNull();
		return actualBook;
	}

	protected <T> ResponseEntity<T> getRequest(String path, Class<T> responseType) {
		return restTemplate.getForEntity(getBaseHost() + path, responseType);
	}

	protected <T> ResponseEntity<T> patchRequest(String path, Class<T> responseType) {
		return restTemplate.exchange(getBaseHost() + path, HttpMethod.PATCH, null, responseType);
	}

	protected <T> ResponseEntity<T> postRequest(String path, Object body, Class<T> responseType) {
		return restTemplate.postForEntity(getBaseHost() + path, body, responseType);
	}

	protected <T> ResponseEntity<T> deleteRequest(String path, Class<T> responseType) {
		return restTemplate.exchange(getBaseHost() + path, HttpMethod.DELETE, null, responseType);
	}

	protected String getBaseHost() {
		return "http://localhost:" + port;
	}

}
