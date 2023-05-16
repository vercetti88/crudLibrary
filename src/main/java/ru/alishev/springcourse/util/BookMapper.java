//package ru.alishev.springcourse.util;
//
//import org.springframework.jdbc.core.RowMapper;
//import ru.alishev.springcourse.models.Book;
//import ru.alishev.springcourse.models.Person;
//
//import javax.swing.tree.TreePath;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BookMapper implements RowMapper<Book>{
//    @Override
//    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//        Book book = new Book();
//        book.setId(resultSet.getInt("id"));
//        book.setOwner_id(resultSet.getInt("owner_id"));
//        book.setName(resultSet.getString("name"));
//        book.setAuthor(resultSet.getString("author"));
//        book.setYear(resultSet.getInt("year"));
//        return book;
//}}