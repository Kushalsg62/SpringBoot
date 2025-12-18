package com.viva;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.viva.entity.Admin;
import com.viva.entity.Book;
import com.viva.entity.BookCopy;
import com.viva.entity.Member;
import com.viva.entity.WishList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookShelfAutomationTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void AddBookTest() throws Exception {
		Book books = new Book();
		books.setBookId(201);
		books.setBookTitle("C language");
		books.setBookAuthor("Yashant Kanetkar");
		books.setBookGenre("Tech");
		books.setBookPrice(220.70f);
		books.setBookPublisher("S.chand");
		books.setBookQuantity(10);
		books.setIsAvailable("true");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void UpdateBookTest() throws Exception {
		Book books = new Book();
		books.setBookTitle("Python language");
		books.setBookAuthor("Sharma S.");
		books.setBookGenre("Tech");
		books.setBookPrice(120.60f);
		books.setBookPublisher("S.chand");
		books.setBookQuantity(10);
		books.setIsAvailable("true");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(put("/books/1338").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void UpdateBookTest1() throws Exception {
		Book books = new Book();
		books.setBookGenre(null);
		books.setBookAuthor(null);
		books.setBookGenre(null);
		books.setBookPrice(20.80f);
		books.setBookPublisher(null);
		books.setBookQuantity(1);
		books.setIsAvailable(null);
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(put("/books/1338").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void UpdateBookTest2() throws Exception {
		Book books = new Book();
		books.setBookTitle("Python language");
		books.setBookAuthor("Sharma S.");
		books.setBookGenre("Tech");
		books.setBookPrice(120.60f);
		books.setBookPublisher("S.chand");
		books.setBookQuantity(10);
		books.setIsAvailable("true");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(put("/books/13345358").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void DeleteBookTest() throws Exception {
		mockMvc.perform(delete("/books/201").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void BookSearchTest() throws Exception {
		Book books = new Book();
		books.setBookTitle("C language");
		books.setBookAuthor("Yashant Kanetkar");
		books.setBookGenre("Tech");
		books.setBookPublisher("S.chand");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest1() throws Exception {
		Book books = new Book();
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest2() throws Exception {
		Book books = new Book();
		books.setBookTitle("C language");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest3() throws Exception {
		Book books = new Book();
		books.setBookAuthor("Yashant Kanetkar");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest4() throws Exception {
		Book books = new Book();
		books.setBookPublisher("S.chand");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest5() throws Exception {
		Book books = new Book();
		books.setBookGenre("Tech");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest6() throws Exception {
		Book books = new Book();
		books.setBookTitle("C language");
		books.setBookAuthor("Yashant Kanetkar");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest7() throws Exception {
		Book books = new Book();
		books.setBookGenre("tech");
		books.setBookAuthor("Yashant Kanetkar");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest8() throws Exception {
		Book books = new Book();
		books.setBookPublisher("S.chand");
		books.setBookAuthor("Yashant Kanetkar");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest9() throws Exception {
		Book books = new Book();
		books.setBookGenre("tech");
		books.setBookPublisher("sfsf");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest10() throws Exception {
		Book books = new Book();
		books.setBookGenre("tech");
		books.setBookTitle("title");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest11() throws Exception {
		Book books = new Book();
		books.setBookPublisher("sdaa");
		books.setBookTitle("title");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest12() throws Exception {
		Book books = new Book();
		books.setBookAuthor("sfsdf");
		books.setBookPublisher("s22aa");
		books.setBookGenre("sdfsr");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void BookSearchTest13() throws Exception {
		Book books = new Book();
		books.setBookAuthor("sfsdf");
		books.setBookTitle("s22aa");
		books.setBookGenre("sdfsr");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest14() throws Exception {
		Book books = new Book();
		books.setBookPublisher("sfsdf");
		books.setBookTitle("s22aa");
		books.setBookGenre("sdfsr");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void BookSearchTest15() throws Exception {
		Book books = new Book();
		books.setBookPublisher("sfsdf");
		books.setBookTitle("s22aa");
		books.setBookAuthor("sdfsr");
		String jsonRequest = om.writeValueAsString(books);
		mockMvc.perform(post("/books/search").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}



	@Test
	public void getBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/books").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books.length > 0);
	}

	@Test
	public void getAllBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/books").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}

	@Test
	public void getBookByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/books/1337").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book books = om.readValue(resultContent, Book.class);
		assertTrue(books != null);
	}

	@Test
	public void getBookIssueTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/1/issue").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}

	@Test
	public void getBookReturnTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/5/return").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}

	@Test
	public void UpdateBookCopyTest() throws Exception {
		BookCopy bookcopy = new BookCopy();
		bookcopy.setItemCondition("3434aaaa");
		bookcopy.setMember(null);
		bookcopy.setIssueDate(null);
		bookcopy.setSubBookId("1337.2");
		String jsonRequest = om.writeValueAsString(bookcopy);
		mockMvc.perform(
				put("/books/1337/copies/1337.2").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void UpdateBookCopyTest1() throws Exception {
		BookCopy bookcopy = new BookCopy("3223adasd","dfdf",1337,5);
		bookcopy.setIssueDate(null);
		bookcopy.setSubBookId("1337.2");
		String jsonRequest = om.writeValueAsString(bookcopy);
		mockMvc.perform(
				put("/books/1337/copies/1337.2").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void deleteBookCopyTest() throws Exception {
		mockMvc.perform(delete("/books/1338/copies/1338.10").content(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andReturn();
	}


	@Test
	public void getAllBookCopyTest() throws Exception {
		mockMvc.perform(get("/books/1337/copies").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getBookCopyTest() throws Exception {
		mockMvc.perform(get("/books/1337/copies/1337.4").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void getBookIssueTest1() throws Exception {
		mockMvc.perform(get("/members/1/issue/1334").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getBookIssueTest2() throws Exception {
		mockMvc.perform(get("/members/3/issue/1200").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getBookIssueTest3() throws Exception {
		mockMvc.perform(get("/members/1/issue/1343").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getBookIssueTest4() throws Exception {
		mockMvc.perform(get("/members/27/issue/1337").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void getBookIssueTest5() throws Exception {
		mockMvc.perform(get("/members/1343343/issue/1334").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getBookReturnTest1() throws Exception {
		mockMvc.perform(get("/members/1/return/1334").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getBookReturnTest2() throws Exception {
		mockMvc.perform(get("/members/3/return/1200").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getBookReturnTest3() throws Exception {
		mockMvc.perform(get("/members/3/return/1338").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

//	@Test
//	public void getBookReturnTest4() throws Exception {
//
//		BookCopy bookcopy = new BookCopy();
//		bookcopy.setSubBookId("1337.2");
//		bookcopy.setItemCondition("gdfg");
//		bookcopy.setMember(new Member(6));
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = format.parse("2019-11-03");
//		bookcopy.setIssueDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//		String jsonRequest = om.writeValueAsString(bookcopy);
//		mockMvc.perform(
//				put("/books/1337/copies/1337.2").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//		mockMvc.perform(get("/members/6/return/1337").content(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//	}

	@Test
	public void getBookReturnTest5() throws Exception {
		mockMvc.perform(get("/members/5/return/1341").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}


	@Test
	public void getWishedBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/books/1334/wishlisted").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}

	@Test
	public void getMemberWishedTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/6/wishlisted").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}

	@Test
	public void alertWishTest() throws Exception {
		mockMvc.perform(get("/wishlist/update").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getWishListByMemberIdTest() throws Exception {
		mockMvc.perform(get("/wishlist/7/1337").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();

	}

	@Test
	public void addWishByMemberTest() throws Exception {
		Member member = new Member();
		member.setMemberId(7);
		String jsonRequest = om.writeValueAsString(member);
		mockMvc.perform(post("/members/7/wishlist").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getWishListByMemberTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/7/wishlist").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}
	
	@Test
	public void getWishListByMemberTest1() throws Exception {
		MvcResult result = mockMvc.perform(get("/wishlist/7").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book[] books = om.readValue(resultContent, Book[].class);
		assertTrue(books != null);
	}

	@Test
	public void getBookWishListTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/wishlist").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		WishList[] wishlist = om.readValue(resultContent, WishList[].class);
		assertTrue(wishlist != null);
	}

	@Test
	public void DeleteWishByMemberIdTest() throws Exception {
		mockMvc.perform(delete("/members/8/wishlist/1337").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void DeleteWishByIdTest() throws Exception {
		mockMvc.perform(delete("/wishlist/2").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void MemberLoginTest() throws Exception {
		Member member = new Member();
		member.setMemberEmail("a@z.com");
		member.setMemberPassword("abcxyz");
		String jsonRequest = om.writeValueAsString(member);
		MvcResult result = mockMvc.perform(post("/memberlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Member member1= om.readValue(resultContent, Member.class);
		assertTrue(member1!=null);
	}
	
	@Test
	public void MemberLoginTest1() throws Exception {
		Member member = new Member();
		member.setMemberEmail("a@z.com1");
		member.setMemberPassword("abcxyz");
		String jsonRequest = om.writeValueAsString(member);
		MvcResult result = mockMvc.perform(post("/memberlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		assertTrue(resultContent.equals(""));
	}
	
	@Test
	public void MemberLoginTest2() throws Exception {
		Member member = new Member();
		member.setMemberEmail("a@z.com");
		member.setMemberPassword("abcxyz1");
		String jsonRequest = om.writeValueAsString(member);
		MvcResult result = mockMvc.perform(post("/memberlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		assertTrue(resultContent.equals(""));
	}



	@Test
	public void AddMemberTest() throws Exception {
		Member member = new Member();
		member.setMemberPassword("password");
		member.setMemberEmail("abc123@gmail.com");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2009-12-31");
		member.setMemberDob(date);
		member.setMemberAddress("abc colony ,banglore");
		String jsonRequest = om.writeValueAsString(member);
		mockMvc.perform(post("/members").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void DeleteMemberTest() throws Exception {
		mockMvc.perform(delete("/members/1").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void DeleteMemberTest1() throws Exception {
		mockMvc.perform(delete("/members/34445345").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void getAllMemberTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Member[] member = om.readValue(resultContent, Member[].class);
		assertTrue(member != null);
	}

	@Test
	public void getMemberByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/1").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Member member = om.readValue(resultContent, Member.class);
		assertFalse(member != null);
	}

	@Test
	public void getMemberByEmailTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/email/bar@foo.com").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Member member = om.readValue(resultContent, Member.class);
		assertTrue(member != null);
	}

	@Test
	public void getMemberTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/members").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Member[] member = om.readValue(resultContent, Member[].class);
		assertTrue(member.length > 0);
	}

	@Test
	public void UpdateMemberTest() throws Exception {
		Member member = new Member();
		member.setMemberPassword("r@mu789");
		member.setBookCopyIssued(null);
		member.setFineAccumulated(0);
		member.setMemberAddress("311 old road mumbai");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2009-12-31");
		member.setMemberDob(date);
		member.setMemberEmail("ramu1@hotmail.com");
		member.setSuspended(false);
		member.setWishedBooksAvailable(null);
		String jsonRequest = om.writeValueAsString(member);
		mockMvc.perform(put("/members/8").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void UpdateMemberTest1() throws Exception {
		Member member = new Member();
		member.setBookCopyIssued(new ArrayList<String>());
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = format.parse("2010-12-31");
//		member.setMemberDob(date);
		member.setSuspended(false);
		member.setWishedBooksAvailable(new ArrayList<Integer>());
		String jsonRequest = om.writeValueAsString(member);
		mockMvc.perform(put("/members/8").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void AdminLoginTest() throws Exception {
		Admin admin = new Admin();
		admin.setAdminEmail("z@a.com");
		admin.setAdminPassword("xyzabc");
		String jsonRequest = om.writeValueAsString(admin);
		MvcResult result = mockMvc.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Admin admin1 = om.readValue(resultContent, Admin.class);
		assertTrue(admin1!=null);
	}

	
	@Test
	public void AdminLoginTest1() throws Exception {
		Admin admin = new Admin();
		admin.setAdminEmail("z@a.com");
		admin.setAdminPassword("xyzabc1");
		String jsonRequest = om.writeValueAsString(admin);
		MvcResult result = mockMvc.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		assertTrue(resultContent.equals(""));
	}
	
	@Test
	public void AdminLoginTest2() throws Exception {
		Admin admin = new Admin();
		admin.setAdminEmail("z@a.com1");
		admin.setAdminPassword("xyzabc");
		String jsonRequest = om.writeValueAsString(admin);
		MvcResult result = mockMvc.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		assertTrue(resultContent.equals(""));
	}

	@Test
	public void getAdminByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/admin/2132").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Admin admin = om.readValue(resultContent, Admin.class);
		assertTrue(admin != null);
	}

	@Test
	public void getAdminByEmailTest() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/admin/email/ljenkins@foobar.com").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Admin admin = om.readValue(resultContent, Admin.class);
		assertTrue(admin != null);
	}

	@Test
	public void adminLoginTest() throws Exception {
		Admin admin = new Admin();
		admin.setAdminId(1001);
		admin.setAdminPassword("password");
		admin.setAdminName("baljeet");
		String jsonRequest = om.writeValueAsString(admin);
		mockMvc.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void AddAdminTest() throws Exception {
		Admin admin = new Admin();
		admin.setAdminId(1001);
		admin.setAdminPassword("password");
		admin.setAdminName("baljeet");
		admin.setAdminEmail("abc123@gmail.com");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2009-12-31");
		admin.setAdminDob(date);
		admin.setAdminAddress("abc colony ,banglore");
		String jsonRequest = om.writeValueAsString(admin);
		mockMvc.perform(post("/admin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void AddAdminTest1() throws Exception {
		Admin admin = new Admin();
		admin.setAdminId(1002);
		admin.setAdminPassword("dfgdfg");
		admin.setAdminName("sumit");
		admin.setAdminEmail("abc123@gmail.com");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2009-12-31");
		admin.setAdminDob(date);
		admin.setAdminAddress("abc colony ,banglore");
		String jsonRequest = om.writeValueAsString(admin);
		mockMvc.perform(post("/admin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
	}

}
