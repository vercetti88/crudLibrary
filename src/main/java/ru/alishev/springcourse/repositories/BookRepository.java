package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByNameStartingWith(String startsWith);
}
