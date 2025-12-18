package com.viva.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.viva.entity.BookCopy;


public interface BookCopyRepository extends CrudRepository<BookCopy, String> {
	
	public List<BookCopy> findByBookBookId(Integer bookId);
	
}
