package com.viva.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.viva.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	public Book getBookByBookId(Integer bookId);

	public List<Book> findByBookAuthor(String bookAuthor);

	public List<Book> findByBookGenre(String bookGenre);

	public List<Book> findByBookPublisher(String bookPublisher);

	public List<Book> findByBookTitle(String bookTitle);
	
	public List<Book> findByIsAvailable(String isAvailable);

	public List<Book> findByBookAuthorAndBookGenre(String bookAuthor, String bookGenre);

	public List<Book> findByBookAuthorAndBookPublisher(String bookAuthor, String bookPublisher);

	public List<Book> findByBookAuthorAndBookTitle(String bookAuthor, String bookTitle);

	public List<Book> findByBookGenreAndBookPublisher(String bookGenre, String bookPublisher);

	public List<Book> findByBookGenreAndBookTitle(String bookGenre, String bookTitle);

	public List<Book> findByBookPublisherAndBookTitle(String bookPublisher, String bookTitle);

	public List<Book> findByBookAuthorAndBookGenreAndBookPublisher(String bookAuthor, String bookGenre,
			String bookPublisher);

	public List<Book> findByBookAuthorAndBookGenreAndBookTitle(String bookAuthor, String bookGenre, String bookTitle);

	public List<Book> findByBookGenreAndBookTitleAndBookPublisher(String bookGenre, String bookTitle,
			String bookPublisher);

	public List<Book> findByBookAuthorAndBookTitleAndBookPublisher(String bookAuthor, String bookTitle,
			String bookPublisher);

	public List<Book> findByBookAuthorAndBookGenreAndBookPublisherAndBookTitle(String bookAuthor, String bookGenre,
			String bookPublisher, String bookTitle);

	public boolean existsByBookId(int bookId);
	
}
