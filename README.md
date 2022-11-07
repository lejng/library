# Library

### Info about app
This app help librarian to control books in library and have the following functions:

* Add, delete, show clients (person who can take book)
* Add, delete, show books
* Assign book to client (Client take book from library)
* Unassign book to client (Client return book to library)
* Link to swagger ui `http://localhost:8080/swagger-ui/index.html`

### In plans
- Add swagger (done)
- Add tests
- Validation
- docker file for run in docker
- Codenarc
- Add roles:
  * Admin - can do all
  * Librarian - can assign and unassign book
  * Unregister user can see books and status busy or not

### Run postgress in docker
```powershell
docker compose up
docker compose down
```