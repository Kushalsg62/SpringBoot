package com.viva.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.BookShelfAutomation;
import com.viva.dao.BookCopyRepository;
import com.viva.dao.MemberRepository;
import com.viva.entity.Book;
import com.viva.entity.BookCopy;
import com.viva.entity.Member;
import com.viva.utils.ShelfLogger;

@Service
public class BookCopyService {

	@Autowired
	private BookCopyRepository bookCopyRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private WishListService wishListService;

	ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	public List<BookCopy> getAllBookCopies(int bookId) {
		List<BookCopy> bookCopies = new ArrayList<>();
		bookCopyRepository.findByBookBookId(bookId).forEach(bookCopies::add);
		return bookCopies;
	}

	public Optional<BookCopy> getBookCopy(String id) {
		return bookCopyRepository.findById(id);
	}

	public void addBookCopy(Book book, int index) {
		BookCopy bookCopy = new BookCopy();
		bookCopy.setSubBookId(Integer.toString(book.getBookId()) + "." + Integer.toString(index));
		bookCopy.setItemCondition("good");
		bookCopy.setBook(book);
		bookCopyRepository.save(bookCopy);
	}

	public void updateBookCopy(BookCopy bookCopy, int bookId) {
		bookCopy.setBook(new Book(bookId));
		bookCopyRepository.save(bookCopy);
	}

	public void deleteBookCopy(String id) {
		if(bookCopyRepository.existsById(id))
		bookCopyRepository.deleteById(id);
		else
			logger.error("Subbook with ID not found!");
	}

	@Transactional
	public int issueBook(int memberId, int bookId) {
		if (!bookService.bookExists(bookId)) {
			logger.error("Book does not Exist!");
			return 1;
		} else {
			if (bookService.getBookById(bookId).getIsAvailable().equalsIgnoreCase("true")) {
				Member issuer = memberRepository.findById(memberId).get();
				if (!issuer.isSuspended()) {
					issueSuccess(bookId, issuer);
					logger.info("Book issued successfully!");
					return 4;
				} else {
					logger.error("You cannot Issue books since you're suspended, pay your fine!");
					return 2;
				}
			} else {
				logger.error("Book is not available!");
				return 3;
			}
		}
	}

	@Transactional
	public void issueSuccess(int bookId, Member issuer) {
		List<BookCopy> bookCopies = getAllBookCopies(bookId);
		Iterator<BookCopy> iterator = bookCopies.iterator();
		while (iterator.hasNext()) {
			BookCopy bookCopy = iterator.next();
			if (bookCopy.getMember() == null) {
				setIssueData(bookId, bookCopy, issuer);
				memberRepository.save(issuer);
				bookService.updateAvailability(bookId);
				wishListService.updateAvailableWishes();
				break;
			}
		}
	}

	@Transactional
	public void setIssueData(int bookId, BookCopy bookCopy, Member issuer) {
		wishListService.deleteWishByMemberIdAndBookId(issuer.getMemberId(), bookId);
		bookCopy.setIssueDate(LocalDate.now());
		bookCopy.setMember(issuer);
		bookCopyRepository.save(bookCopy);
		List<String> bookCopyIssued = issuer.getBookCopyIssued();
		bookCopyIssued.add(bookCopy.getSubBookId());
		issuer.setBookCopyIssued(bookCopyIssued);
		List<Integer> wishListedBooks = issuer.getWishedBooksAvailable();
		if (wishListedBooks != null)
			wishListedBooks.removeIf(wishBookId -> wishBookId.equals(bookId));
		issuer.setWishedBooksAvailable(wishListedBooks);
	}

	public int returnBook(int memberId, int bookId) {
		if (bookService.bookExists(bookId)) {
			boolean flag = true;
			Member issuer = memberRepository.findById(memberId).get();
			List<String> bookCopiesIssued = issuer.getBookCopyIssued();
			Iterator<String> iterator = bookCopiesIssued.iterator();
			while (iterator.hasNext()) {
				BookCopy issuedCopy = bookCopyRepository.findById(iterator.next()).get();
				if (Integer.parseInt(issuedCopy.getSubBookId().substring(0, Integer.toString(bookId).length())) == bookId) {
					setReturnData(issuer, bookCopiesIssued, issuedCopy, bookId);
					flag = false;
					break;
				}
			}
			if (flag) {
				logger.info("Book " + bookId + " not Issued by you!");
				return 1;
			} else {
				logger.info("Book returned successfully!");
				return 3;
			}
		} else {
			logger.info("Book does not exists!");
			return 2;
		}
	}

	public void setReturnData(Member issuer, List<String> bookCopiesIssued, BookCopy issuedCopy, int bookId) {
		issuer.setFineAccumulated(
				issuer.getFineAccumulated() + generateFine(issuedCopy.getIssueDate(), LocalDate.now()));
		if (issuer.getFineAccumulated() > 100)
			issuer.setSuspended(true);
		bookCopiesIssued.remove(issuedCopy.getSubBookId());
		issuer.setBookCopyIssued(bookCopiesIssued);
		memberRepository.save(issuer);
		issuedCopy.setMember(null);
		issuedCopy.setIssueDate(null);
		bookCopyRepository.save(issuedCopy);
		bookService.updateAvailability(bookId);
		wishListService.updateAvailableWishes();
	}

	private float generateFine(LocalDate issueDate, LocalDate returnDate) {
		float fine;
		long elapsedDays = ChronoUnit.DAYS.between(issueDate, returnDate);
		if (elapsedDays < 7)
			fine = 0;
		else if (elapsedDays >= 7 && elapsedDays < 15)
			fine = 12.5f;
		else
			fine = (float) (1.5 * elapsedDays);
		return fine;
	}

	public void removeBookCopy(int id, int index) {
		String bookCopyId = Integer.toString(id) + "." + Integer.toString(index);
		bookCopyRepository.deleteById(bookCopyId);
	}

}
