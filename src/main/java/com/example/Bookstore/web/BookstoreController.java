package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@Controller
public class BookstoreController {

	@Autowired
	private BookRepository repository;

	@RequestMapping(value = { "/", "/booklist" }, method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		System.out.println("HELLO BEBE");
		return "booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());

		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		//.save toimii niin, että jos tallennettavan kirjan ID täsmää jo taulussa olevan kirjan ID:seen,
		// tallennetaan kirja silloin kyseisen ID:n kohdalle
		repository.save(book);

		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {

		model.addAttribute("book", repository.findById(bookId));

		return "editbook";
	}

}
