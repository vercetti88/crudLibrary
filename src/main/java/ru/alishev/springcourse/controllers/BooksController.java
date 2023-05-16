package ru.alishev.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.BookService;
import ru.alishev.springcourse.services.PeopleService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    private final PeopleService peopleService;



    @Autowired
    public BooksController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(@RequestParam(name = "page") Optional<Integer> page,
                        @RequestParam(name = "books_per_page") Optional<Integer> bookPerPage,
                        @RequestParam(name = "sort_by_year") Optional<Boolean> sortByYear, Model model){

        if(page.isPresent() && bookPerPage.isPresent() && sortByYear.isPresent()){
            model.addAttribute("books",bookService.findPagedBooksSortedByYear(page.get(),bookPerPage.get()));
        }else if(page.isPresent() && bookPerPage.isPresent()){
            model.addAttribute("books",bookService.findPagedBooks(page.get(),bookPerPage.get()));
        }else if(sortByYear.isPresent()){
            model.addAttribute("books",bookService.findSortedPages());
        }
        else{
            model.addAttribute("books", bookService.findAll());
        }

        return "books/index";
    }



    @GetMapping("/search")
    public String show(@RequestParam(value = "startsWith",required = false) String startsWith, Model model){
        if(startsWith!=null) {
            model.addAttribute("books", bookService.findByNameStartingWith(startsWith));
        }
        model.addAttribute("startsWith", startsWith);
        return "books/search";
    }





    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){

        model.addAttribute("book", bookService.findOne(id));

        Optional<Person> bookOwner = bookService.getOwnerById(id);
        if(bookOwner.isPresent())
            model.addAttribute("owner",bookOwner.get());
        else
            model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookService.deleteOwner(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/set-owner")
    public String setOwner(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookService.setOwner(id, person.getId());
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id){
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        bookService.update(id,book);
        System.out.println("Salam aleykum");
        return "redirect:/books";
    }


}
