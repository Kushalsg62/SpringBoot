package com.viva.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.BookShelfAutomation;
import com.viva.dao.BookRepository;
import com.viva.dao.MemberRepository;
import com.viva.entity.Book;
import com.viva.entity.BookCopy;
import com.viva.entity.Member;
import com.viva.utils.ShelfLogger;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	BookCopyService bookCopyService;

	ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}

	public Optional<Book> getBook(int id) {
		return bookRepository.findById(id);
	}

	public void addBook(Book book) {
		bookRepository.save(book);
		for (int i = 0; i < book.getBookQuantity(); i++) {
			bookCopyService.addBookCopy(book, i + 1);
		} // this loop automatically creates SubBook Entities for new book
	}

	public void updateBook(Book book, int id) {
		if (bookRepository.findById(id).isPresent()) {
			setOldData(book,id);
			
			if (book.getBookQuantity() != bookRepository.findById(id).get().getBookQuantity()) {
				for (int i = 0; i < bookRepository.findById(id).get().getBookQuantity(); i++)
					bookCopyService.removeBookCopy(id, i + 1);
				for (int i = 0; i < book.getBookQuantity(); i++)
					bookCopyService.addBookCopy(bookRepository.findById(id).get(), i + 1);
			}

			book.setBookId(id);
			bookRepository.save(book);
		} else		
			logger.error("Book with this ID does not Exists!");
	}
	
	public void setOldData(Book book,int id) {
		if (book.getBookAuthor() == null)
			book.setBookAuthor(getBookById(id).getBookAuthor());
		if (book.getBookGenre() == null)
			book.setBookGenre(getBookById(id).getBookGenre());
		if (book.getBookPrice() == 0)
			book.setBookPrice(getBookById(id).getBookPrice());
		if (book.getBookPublisher() == null)
			book.setBookPublisher(getBookById(id).getBookPublisher());
		if (book.getBookTitle() == null)
			book.setBookTitle(getBookById(id).getBookTitle());
		if (book.getIsAvailable() == null)
			book.setIsAvailable(getBookById(id).getIsAvailable());
	}

	public void deleteBook(int id) {
		if (bookRepository.existsById(id)) {
			Book book = bookRepository.findById(id).get();
			for (int i = 0; i < book.getBookQuantity(); i++) {
				bookCopyService.removeBookCopy(id, i + 1);
			} // this loop automatically deletes SubBook Entities for deleted book
			bookRepository.deleteById(id);
		}
	}

	public Book getBookById(int id) {
		return bookRepository.getBookByBookId(id);
	}

	public List<Book> searchBook(String bookAuthor, String bookGenre, String bookPublisher, String bookTitle) {

		List<Book> searchResults;
		int countNulls = 0;

		if (bookAuthor == null)
			countNulls++;
		if (bookGenre == null)
			countNulls++;
		if (bookPublisher == null)
			countNulls++;
		if (bookTitle == null)
			countNulls++;

		switch (countNulls) {
		case 0:
			searchResults = searchBookByAllFields(bookAuthor, bookGenre, bookPublisher, bookTitle);
			break;
		case 1:
			searchResults = searchBookByThreeFields(bookAuthor, bookGenre, bookPublisher, bookTitle);
			break;
		case 2:
			searchResults = searchBookByTwoFields(bookAuthor, bookGenre, bookPublisher, bookTitle);
			break;
		case 3:
			searchResults = searchBookByOneField(bookAuthor, bookGenre, bookPublisher, bookTitle);
			break;
		case 4:
			searchResults = getAllBooks();
			break;
		default:
			logger.error("No Books Found!");
			searchResults = Collections.emptyList();
			break;
		}
		return searchResults;
	}

	public List<Book> searchBookByOneField(String bookAuthor, String bookGenre, String bookPublisher,
			String bookTitle) {
		if (bookGenre == null && bookPublisher == null && bookTitle == null)
			return bookRepository.findByBookAuthor(bookAuthor);
		else if (bookAuthor == null && bookPublisher == null && bookTitle == null)
			return bookRepository.findByBookGenre(bookGenre);
		else if (bookAuthor == null && bookGenre == null && bookTitle == null)
			return bookRepository.findByBookPublisher(bookPublisher);
		else
			return bookRepository.findByBookTitle(bookTitle);
	}

	public List<Book> searchBookByTwoFields(String bookAuthor, String bookGenre, String bookPublisher,
			String bookTitle) {
		if (bookPublisher == null && bookTitle == null)
			return bookRepository.findByBookAuthorAndBookGenre(bookAuthor, bookGenre);
		else if (bookGenre == null && bookTitle == null)
			return bookRepository.findByBookAuthorAndBookPublisher(bookAuthor, bookPublisher);
		else if (bookGenre == null && bookPublisher == null)
			return bookRepository.findByBookAuthorAndBookTitle(bookAuthor, bookTitle);
		else if (bookAuthor == null && bookTitle == null)
			return bookRepository.findByBookGenreAndBookPublisher(bookGenre, bookPublisher);
		else if (bookAuthor == null && bookPublisher == null)
			return bookRepository.findByBookGenreAndBookTitle(bookGenre, bookTitle);
		else 
			return bookRepository.findByBookPublisherAndBookTitle(bookPublisher, bookTitle);
	}

	public List<Book> searchBookByAllFields(String bookAuthor, String bookGenre, String bookPublisher,
			String bookTitle) {
		return bookRepository.findByBookAuthorAndBookGenreAndBookPublisherAndBookTitle(bookAuthor, bookGenre,
				bookPublisher, bookTitle);
	}

	public List<Book> searchBookByThreeFields(String bookAuthor, String bookGenre, String bookPublisher,
			String bookTitle) {
		if (bookTitle == null)
			return bookRepository.findByBookAuthorAndBookGenreAndBookPublisher(bookAuthor, bookGenre, bookPublisher);
		else if (bookPublisher == null)
			return bookRepository.findByBookAuthorAndBookGenreAndBookTitle(bookAuthor, bookGenre, bookTitle);
		else if (bookAuthor == null)
			return bookRepository.findByBookGenreAndBookTitleAndBookPublisher(bookGenre, bookTitle, bookPublisher);
		else 
			return bookRepository.findByBookAuthorAndBookTitleAndBookPublisher(bookAuthor, bookTitle, bookPublisher);
	}

	public List<Book> getAvailableBooks() {
		if (bookRepository.findByIsAvailable("true") == null || bookRepository.findByIsAvailable("true").isEmpty()) {
			logger.info("All books are issued! Check back again.");
			return Collections.emptyList();
		}
		return bookRepository.findByIsAvailable("true");
	}

	public boolean bookExists(int bookId) {
		return bookRepository.existsByBookId(bookId);
	}

	public List<Book> getIssuedBooks(int memberId) throws IOException {
		ArrayList<Book> booksIssued = new ArrayList<>();
		if (!memberRepository.findById(memberId).isPresent()) {
			logger.error("Invalid Member!");
			return booksIssued;
		}
		Member issuer = memberRepository.findById(memberId).get();
		logger.info("issuerId : " + issuer.getMemberId());
		if (issuer.getBookCopyIssued().isEmpty()) {
			logger.info("You have no issued books!");
			return booksIssued;
		}
		List<String> bookCopiesId = issuer.getBookCopyIssued();
		logger.info("List of BookCopies issued : " + bookCopiesId);
		Iterator<String> iterator = bookCopiesId.iterator();
		while (iterator.hasNext()) {
			String bookCopyId = iterator.next();
			int bookIssuedId = Integer.parseInt(bookCopyId.substring(0, 4));
			logger.info("bookIssuedId : " + bookIssuedId);
			booksIssued.add(bookRepository.getBookByBookId(bookIssuedId));
		}
		return booksIssued;
	}

	public void updateAvailability(int bookId) {
		Book book = bookRepository.getBookByBookId(bookId);
		book.setIsAvailable("false");
		List<BookCopy> bookCopies = bookCopyService.getAllBookCopies(bookId);
		Iterator<BookCopy> iterator = bookCopies.iterator();
		while (iterator.hasNext()) {
			BookCopy bookCopy = iterator.next();
			if (bookCopy.getIssueDate() == null) {
				book.setIsAvailable("true");
			}
		}
		bookRepository.save(book);
	}
}
