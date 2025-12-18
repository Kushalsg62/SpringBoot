package com.viva.client;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.BookShelfAutomation;
import com.viva.entity.BookCopy;
import com.viva.service.BookCopyService;
import com.viva.service.WishListService;
import com.viva.utils.ShelfLogger;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookCopyController {
	
	ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	@Autowired
	private BookCopyService bookCopyService;

	@Autowired
	HttpServletResponse httpResponse;
	
	@Autowired
	WishListService wishListService;

	@RequestMapping("/books/{bookId}/copies")
	public List<BookCopy> getAllBookCopies(@PathVariable int bookId) {
		return bookCopyService.getAllBookCopies(bookId);
	}

	@RequestMapping("/books/{bookId}/copies/{id}")
	public Optional<BookCopy> getBookCopy(@PathVariable String id) {
		return bookCopyService.getBookCopy(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/books/{bookId}/copies/{id}")
	public void updateBookCopy(@RequestBody BookCopy bookCopy, @PathVariable int bookId, @PathVariable String id) {		
		bookCopyService.updateBookCopy(bookCopy,bookId);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/books/{bookId}/copies/{id}")
	public void deleteBookCopy(@PathVariable String id) {
		bookCopyService.deleteBookCopy(id);
	}

	@RequestMapping("/members/{memberId}/issue/{bookId}")
	public int issueBook(@PathVariable int memberId, @PathVariable int bookId) throws IOException {
		return bookCopyService.issueBook(memberId, bookId);
	}

	@RequestMapping("/members/{memberId}/return/{bookId}")
	public int returnBook(@PathVariable int memberId, @PathVariable int bookId) throws IOException {		
		return bookCopyService.returnBook(memberId, bookId);				
	}

}
