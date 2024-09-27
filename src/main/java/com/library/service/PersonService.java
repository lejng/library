package com.library.service;

import com.library.dto.BookDto;
import com.library.dto.CreatePersonDto;
import com.library.entity.Person;
import com.library.dto.PersonDto;
import com.library.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<PersonDto> find(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).stream().map(PersonDto::from).toList();
    }

    @Transactional(readOnly = true)
    public Optional<PersonDto> byId(long id) {
        return repository.findById(id).map(PersonDto::from);
    }

    @Transactional
    public PersonDto save(CreatePersonDto personDto) {
        Person person = new Person(personDto.name(), personDto.surname());
        repository.save(person);
        return PersonDto.from(person);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

}
