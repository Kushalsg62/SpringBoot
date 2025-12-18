package com.viva.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.BookShelfAutomation;
import com.viva.dao.BookRepository;
import com.viva.dao.MemberRepository;
import com.viva.dao.WishListRepository;
import com.viva.entity.Book;
import com.viva.entity.Member;
import com.viva.entity.WishList;
import com.viva.utils.ShelfLogger;

@Service
public class WishListService {

	@Autowired
	private WishListRepository wishListRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	
	public Book getBookByBookId(int id) {
		return bookRepository.getBookByBookId(id);
	}

	public List<WishList> getWishesByMemberId(int memberId) {
		List<WishList> wishes = new ArrayList<>();
		wishListRepository.findByMemberMemberId(memberId).forEach(wishes::add);
		return wishes;
	}

	public List<WishList> getWishesByBookId(int bookId) {
		List<WishList> wishes = new ArrayList<>();
		wishListRepository.findByBookBookId(bookId).forEach(wishes::add);
		return wishes;
	}

	public List<WishList> getAllWishes() {
		return (List<WishList>) wishListRepository.findAll();
	}

	public void addWish(WishList wishList, int memberId) {
		Book b = getBookByBookId(wishList.getBookId());
		if (null != b) {
			if (b.getIsAvailable().equals("false")) {
				wishList.setBook(b);
				wishList.setMember(new Member(memberId));
				wishListRepository.save(wishList);
			}
			else
				logger.info("Book is Available! No need to Wishlist");
		}
		else logger.error("Book Not Found!");
	}
		

	public void deleteWish(int id) {
		if(wishListRepository.existsById(id))
		wishListRepository.deleteById(id);
		else
			logger.error("Invalid Wish ID!");
	}

	public WishList getWishByMemberIdAndId(int memberId, int id) {
		return wishListRepository.findByMemberMemberIdAndWishListId(memberId, id);
	}

	@Transactional
	public void deleteWishByMemberIdAndBookId(int memberId, int bookId) {
		wishListRepository.deleteByMemberMemberIdAndBookBookId(memberId, bookId);
	}
	
	public void deleteWishByMemberIdAndId(int memberId, int id) {
		wishListRepository.deleteByMemberMemberIdAndWishListId(memberId, id);
	}

	public List<Book> getWishableBooks() {
		List<Book> allBooks = new ArrayList<>();
		List<Book> wishableBooks = new ArrayList<>();
		bookRepository.findAll().forEach(allBooks::add);
		Iterator<Book> iterator = allBooks.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			String isAvailable = book.getIsAvailable();
			if (isAvailable.equals("false"))
				wishableBooks.add(book);
		}
		return wishableBooks;
	}

	public void updateAvailableWishes() {
		List<Member> allMembers = new ArrayList<>();
		memberRepository.findAll().forEach(allMembers::add);
		Iterator<Member> memberIterator = allMembers.iterator();
		while (memberIterator.hasNext()) {
			ArrayList<Integer> pushableWishes = new ArrayList<>();
			Member member = memberIterator.next();
			List<WishList> memberWishes = getWishesByMemberId(member.getMemberId());
			Iterator<WishList> wishIterator = memberWishes.iterator();
			while (wishIterator.hasNext()) {
				WishList wish = wishIterator.next();
				if (wish.getBook().getIsAvailable().equals("true"))
					pushableWishes.add(wish.getBook().getBookId());
			}
			member.setWishedBooksAvailable(pushableWishes);
			memberRepository.save(member);
		}
	}

}
