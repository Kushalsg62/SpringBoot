package com.viva.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.BookShelfAutomation;
import com.viva.entity.Book;
import com.viva.entity.WishList;
import com.viva.service.WishListService;
import com.viva.utils.ShelfLogger;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishListController {

	@Autowired
	private WishListService wishListService;

	ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	@RequestMapping("/wishlist")
	public List<WishList> getWishList() {
		return wishListService.getAllWishes();
	}

	@RequestMapping("/wishlist/{memberId}")
	public List<WishList> getWishesByMember(@PathVariable int memberId) {
		return wishListService.getWishesByMemberId(memberId);
	}

	@RequestMapping("/wishlist/{memberId}/{id}")
	public WishList getWishByMember(@PathVariable int memberId, @PathVariable int id) {
		return wishListService.getWishByMemberIdAndId(memberId, id);
	}

	@RequestMapping("/members/{memberId}/wishlist")
	public List<Book> getWishableBooks(@PathVariable int memberId) {
		return wishListService.getWishableBooks();
	}

	@RequestMapping("/books/{bookId}/wishlisted")
	public List<WishList> getWishedBook(@PathVariable int bookId) {
		return wishListService.getWishesByBookId(bookId);
	}

	@RequestMapping("/members/{memberId}/wishlisted")
	public List<WishList> getMemberWishes(@PathVariable int memberId) {
		return wishListService.getWishesByMemberId(memberId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/members/{memberId}/wishlist")
	public void addWishByMember(@RequestBody WishList wishList, @PathVariable int memberId) {
		wishListService.addWish(wishList,memberId);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/wishlist/{id}")
	public void deleteWish(@PathVariable int id) {
		wishListService.deleteWish(id);
	}
	

	@RequestMapping(method = RequestMethod.DELETE, value = "/members/{memberId}/wishlist/{id}")
	public void deleteWishByMember(@PathVariable int id, @PathVariable int memberId) {
		wishListService.deleteWishByMemberIdAndId(memberId, id);
	}

	@RequestMapping("/wishlist/update")
	public void alertWishes() {
		wishListService.updateAvailableWishes();
	}

}
