package com.viva.dao;


import org.springframework.data.repository.CrudRepository;

import com.viva.entity.Admin;


public interface AdminRepository extends CrudRepository<Admin,Integer> { //Interface for Admin CRUDRepository which interacts with DB
	public Admin getAdminByAdminEmail(String adminEmail);
}
