package ru.alishev.springcourse.services;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;
import ru.alishev.springcourse.repositories.PeopleRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final EntityManager entityManager;

    private final BookRepository bookRepository;

    private final PeopleRepository peopleRepository;


    @Autowired
    public BookService(EntityManager entityManager, BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.entityManager = entityManager;
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public List<Book> findByNameStartingWith(String startsWith){
        return bookRepository.findByNameStartingWith(startsWith);
    }

    @Transactional
    public List<Book> findPagedBooks(int page, int booksPerPage){
        return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
    }

    @Transactional
    public List<Book> findPagedBooksSortedByYear(int page, int booksPerPage){
        return bookRepository.findAll(PageRequest.of(page,booksPerPage,
                Sort.by("year"))).getContent();
    }

    @Transactional
    public List<Book> findSortedPages(){
        return bookRepository.findAll(Sort.by("year"));
    }


    @Transactional
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book findOne(int id){
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }


    @Transactional
    public void save(Book book){
        book.setDateTheBookPicked(new Date());
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id,Book book){
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setOwner(int bookId, int personId){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, bookId);
        Person owner = session.get(Person.class, personId);
        book.setOwner(owner);
        owner.addBook(book);
        book.setDateTheBookPicked(new Date());
    }

    @Transactional
    public Optional<Person> getOwnerById(int id){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class,id);
        Optional<Person> person = Optional.ofNullable(book.getOwner());
        return Optional.ofNullable(book.getOwner());
    }

    @Transactional
    public void deleteOwner(int id){
        Session session = entityManager.unwrap(Session.class);
        Book book = session.get(Book.class, id);
        book.setDateTheBookPicked(null);
        Person person = book.getOwner();
        person.deleteBook(book);
        book.setOwner(null);
    }


}
