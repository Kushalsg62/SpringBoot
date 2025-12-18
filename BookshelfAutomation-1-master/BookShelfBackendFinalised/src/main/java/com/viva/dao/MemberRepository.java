package com.viva.dao;


import org.springframework.data.repository.CrudRepository;

import com.viva.entity.Member;


public interface MemberRepository extends CrudRepository<Member,Integer> {

	Member getMemberByMemberEmail(String memberEmail);
	
}
