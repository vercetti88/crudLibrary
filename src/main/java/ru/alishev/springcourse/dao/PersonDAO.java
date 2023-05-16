//package ru.alishev.springcourse.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import ru.alishev.springcourse.models.Book;
//import ru.alishev.springcourse.models.Person;
//import ru.alishev.springcourse.util.BookMapper;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author Neil Alishev
// */
//@Component
//public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
//                stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name,age,email,address) VALUES(?, ?, ?, ?)", person.getName(), person.getAge(),
//                person.getEmail(), person.getAddress());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?", updatedPerson.getName(),
//                updatedPerson.getAge(), updatedPerson.getEmail(),updatedPerson.getAddress(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public Optional<Person> show(String email){
//        return jdbcTemplate.query("SELECT * FROM Person where email=?", new Object[]{email},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public List<Book> getBooks(int personID){
//       return jdbcTemplate.query("select * from book where owner_id=?", new Object[]{personID}, new BookMapper());
//    }
//
//
//
//
//
//}