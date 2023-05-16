package ru.alishev.springcourse.services;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.PeopleRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PeopleService {
    private final EntityManager entityManager;

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(EntityManager entityManager, PeopleRepository peopleRepository) {
        this.entityManager = entityManager;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    @Transactional
    public Person findOne(int id){
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    @Transactional
    public List<Book> getBooks(int id){
        Session session = entityManager.unwrap(Session.class);
        Person person = session.get(Person.class, id);
        List<Book> books = person.getBooks();
        long currentDate = System.currentTimeMillis();
        for(Book book: books){
            int difference = (int) (currentDate - book.getDateTheBookPicked().getTime());
            if(TimeUnit.MILLISECONDS.toDays(difference)>10){
                book.setBookExpired(true);
            }
        }
        return books;
    }

    @Transactional
    public Optional<Person> findByEmail(String email){
        Optional<Person> person = Optional.ofNullable(peopleRepository.findByEmail(email));
        return Optional.ofNullable(person.orElse(null));
    }

}
