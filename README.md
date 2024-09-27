# Library

### About the App
This app helps librarians manage books in the library. It includes the following features:

* Add, delete, and view clients (people who can borrow books)
* Add, delete, and view books
* Assign a book to a client (client borrows a book from the library)
* Unassign a book from a client (client returns the book to the library)

### Planned Features
- Add Swagger (done)
- Add tests
- Add validation
- Create a Dockerfile to run in Docker
- Add CodeNarc for code analysis
- Add user roles:
  * Admin - can perform all actions
  * Librarian - can assign and unassign books
  * Unregistered users - can view books and their availability

### Running the App
- Java 17 must be installed
- Start the database in Docker ```docker compose up```
- Run the app ```./gradlew bootRun```
- Access Swagger UI at `http://localhost:8080/swagger-ui/index.html`
- Run tests: ```./gradlew test```

### Running PostgreSQL in Docker
- Start the Docker container ```docker compose up```
- Stop the Docker container ```docker compose down```
