package ru.alishev.springcourse.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private boolean bookExpired;

    public boolean isBookExpired() {
        return bookExpired;
    }

    public void setBookExpired(boolean bookExpired) {
        this.bookExpired = bookExpired;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private Person owner;

    @Column(name = "name")
    @NotEmpty(message = "Book's title should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "the_date_book_picked")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTheBookPicked;

    public Date getDateTheBookPicked() {
        return dateTheBookPicked;
    }

    public void setDateTheBookPicked(Date dateTheBookPicked) {
        this.dateTheBookPicked = dateTheBookPicked;
    }

    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    private String author;

    @Column(name = "year")
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book(){

    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
