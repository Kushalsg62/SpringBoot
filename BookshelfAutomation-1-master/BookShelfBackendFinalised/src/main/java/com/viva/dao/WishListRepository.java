package com.viva.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.viva.entity.WishList;


public interface WishListRepository extends CrudRepository<WishList, Integer> {
	
	public List<WishList> findByMemberMemberId(Integer memberId);
	public List<WishList> findByBookBookId(Integer bookId);
	public void deleteByMemberMemberId(Integer memberId);
	public WishList findByMemberMemberIdAndWishListId(Integer memberId,Integer id);
	public void deleteByMemberMemberIdAndWishListId(Integer memberId,Integer id);
	public void deleteByMemberMemberIdAndBookBookId(Integer memberId, Integer bookId);
	
}
