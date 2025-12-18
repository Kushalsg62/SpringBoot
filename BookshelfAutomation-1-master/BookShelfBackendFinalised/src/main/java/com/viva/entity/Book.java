package com.viva.entity;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	private int    bookId;
	private String bookTitle;
	private String bookAuthor;
	private String bookGenre;
	private String bookPublisher;
	private float  bookPrice;
	private int    bookQuantity;
	private String isAvailable;


	public Book() {
		
	}
	
    public Book(int bookId) {
    	super();
		this.bookId = bookId;
	}
	
	public Book(int bookId, String bookTitle, String bookAuthor, String bookGenre, String bookPublisher,
			float bookPrice, int bookQuantity) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookGenre = bookGenre;
		this.bookPublisher = bookPublisher;
		this.bookPrice = bookPrice;
		this.bookQuantity = bookQuantity;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	
	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	

}
