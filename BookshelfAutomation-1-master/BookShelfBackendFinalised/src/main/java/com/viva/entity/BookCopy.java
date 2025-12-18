package com.viva.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookCopy {

	@Id
	private String subBookId;

	private String itemCondition;
	
	private LocalDate issueDate;


	@ManyToOne
	private Book book;

	@ManyToOne
	private Member member;

	public BookCopy() {

	}

	public BookCopy(String subBookId, String itemCondition, int bookId, int memberId) {
		super();
		this.subBookId = subBookId;
		this.itemCondition = itemCondition;
		this.book = new Book(bookId);
		this.member = new Member(memberId);
	}

	public String getSubBookId() {
		return subBookId;
	}

	public void setSubBookId(String subBookId) {
		this.subBookId = subBookId;
	}

	public String getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
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
	
	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

}
