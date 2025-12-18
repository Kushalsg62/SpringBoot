package com.viva.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class WishList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishListId;
	
	@Transient //will not be a column
	private int bookId;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Member member;

	public WishList() {

	}

	public WishList(int wishListId, int bookId, int memberId) {
		super();
		this.wishListId = wishListId;
		this.book = new Book(bookId);
		this.member = new Member(memberId);
	}
	

	public int getWishListId() {
		return wishListId;
	}

	public void setWishListId(int wishListId) {
		this.wishListId = wishListId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


}
