package com.viva.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberId;
	
	private String memberPassword;
	private Date memberDob;
	private String memberAddress;
	
	@Column(unique=true)
	private String memberEmail;
	
	private boolean isSuspended;
	private ArrayList<String> bookCopyIssued;	
	private float fineAccumulated;
	private ArrayList<Integer> wishedBooksAvailable;	


	public Member() {

	}

	public Member(int id) {
		super();
		this.memberId = id;
	}

	public Member(int memberId, String memberPassword, Date memberDob, String memberAddress, String memberEmail,
			boolean isSuspended) {
		super();
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberDob = memberDob;
		this.memberAddress = memberAddress;
		this.memberEmail = memberEmail;
		this.isSuspended = isSuspended;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public Date getMemberDob() {
		return memberDob;
	}

	public void setMemberDob(Date memberDob) {
		this.memberDob = memberDob;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	@Column(unique=true)
	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public float getFineAccumulated() {
		return fineAccumulated;
	}

	public void setFineAccumulated(float fineAccumulated) {
		this.fineAccumulated = fineAccumulated;
	}

	public List<String> getBookCopyIssued() {
		return bookCopyIssued;
	}
	
	public void setBookCopyIssued(List<String> bookCopyIssued) {
		this.bookCopyIssued = (ArrayList<String>) bookCopyIssued;
	}
	
	public List<Integer> getWishedBooksAvailable() {
		return wishedBooksAvailable;
	}

	public void setWishedBooksAvailable(List<Integer> wishedBooksAvailable) {
		this.wishedBooksAvailable = (ArrayList<Integer>) wishedBooksAvailable;
	}

}
