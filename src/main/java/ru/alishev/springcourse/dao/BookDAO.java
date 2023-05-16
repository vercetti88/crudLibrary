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
//@Component
//public class BookDAO {
//    private final PersonDAO personDAO;
//
//    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    public BookDAO(PersonDAO personDAO, JdbcTemplate jdbcTemplate) {
//        this.personDAO = personDAO;
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index(){
//        return  jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
//    }
//
//    public void save(Book book){
//        jdbcTemplate.update("INSERT INTO Book(name,author,year) VALUES (?,?,?)",book.getName(),book.getAuthor(),book.getYear());
//    }
//
//    public Book show(int id){
//        return jdbcTemplate.query("SELECT * FROM Book  where id =?", new Object[]{id},new BookMapper()).stream().findAny().orElse(null);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//    public void update(int id, Book book){
//        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year = ? where id=?",
//                book.getName(),book.getAuthor(),book.getYear(), id);
//    }
//
//    public void setOwner(int bookID, int ownerID){
//        jdbcTemplate.update("UPDATE Book set owner_id=? where id=?",ownerID,bookID);
//    }
//
//    public void deleteOwner(int id){
//        jdbcTemplate.update("UPDATE Book set owner_id=null where id=?", id);
//    }
//
//    public Optional<Person> getOwner(int id){
//        return Optional.ofNullable(personDAO.show(show(id).getOwner_id()));
//    }
//
//}
