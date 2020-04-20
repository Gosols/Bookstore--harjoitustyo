package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class BookstoreController {

    @Autowired
    private BookRepository brepo;

    @Autowired
    private CategoryRepository crepo;

    // show all books
    @RequestMapping(value = {"/", "/booklist"}, method = RequestMethod.GET)
    public String bookList(Model model) {
        model.addAttribute("books", brepo.findAll());
        return "booklist";
    }

    // add a book
    @RequestMapping(value = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepo.findAll());

        return "addbook";
    }

    // save a new book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveBook(Book book) {
        // .save toimii niin, että jos tallennettavan kirjan ID täsmää jo taulussa
        // olevan kirjan ID:seen,
        // tallennetaan kirja silloin kyseisen ID:n kohdalle
        brepo.save(book);

        return "redirect:booklist";
    }

    // delete a book
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        brepo.deleteById(bookId);

        return "redirect:../booklist";
    }

    // edit a book
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editBook(@PathVariable("id") Long bookId, Model model) {


        model.addAttribute("book", brepo.findById(bookId));
        model.addAttribute("categories", crepo.findAll());

        return "editbook";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    //REST methods
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    List<Book> booksRest() {
        return (List<Book>) brepo.findAll();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    private @ResponseBody
    Optional<Book> bookByIdRest(@PathVariable("id") Long bookId) {
        return brepo.findById(bookId);
    }

}
