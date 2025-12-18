package com.viva.client;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.entity.Admin;
import com.viva.service.AdminService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/admin/{id}")
	public Optional<Admin> getAdmin(@PathVariable int id) {
		return adminService.getAdmin(id);
	}
	
	@RequestMapping("/admin/email/{email}")
	public Admin getAdmin(@PathVariable String email) {
		return adminService.getAdminByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/admin")
	public void addAdmin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
		adminService.addAdmin(admin);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/adminlogin")
	public Admin adminLogin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
		return adminService.getValidation(admin);
	}
}
