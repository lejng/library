package com.library.entity;

import javax.persistence.*;

@Entity(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence_gen")
    @SequenceGenerator(name = "book_sequence_gen", sequenceName = "book_sequence", allocationSize = 1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {

    }

    public Book(String name, String author, Person person) {
        this(name, author);
        this.person = person;
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
