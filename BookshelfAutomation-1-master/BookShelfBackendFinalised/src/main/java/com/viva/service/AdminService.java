package com.viva.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.utils.Hasher;
import com.viva.utils.ShelfLogger;
import com.viva.BookShelfAutomation;
import com.viva.dao.AdminRepository;
import com.viva.entity.Admin;

@Service
public class AdminService {// Service Layer for Admin

	@Autowired
	private AdminRepository adminRepository;

	ShelfLogger logger = new ShelfLogger(BookShelfAutomation.class);

	public Optional<Admin> getAdmin(int id) {
		return adminRepository.findById(id);
	}

	public Admin getAdminByEmail(String email) {
		return adminRepository.getAdminByAdminEmail(email);
	}

	public void addAdmin(Admin admin) {
		try {
			admin.setAdminPassword(Hasher.hashingFunction(admin.getAdminPassword()));
			adminRepository.save(admin);
		} catch (Exception e) {
			logger.error("Email Already in Use!");
		}
	}

	public Admin validatedAdmin(Admin admin) throws NoSuchAlgorithmException {

		if (getAdminByEmail(admin.getAdminEmail()) == null) {
			logger.error("Email not Registered!");
		} else {
			Admin adminFromDb = getAdminByEmail(admin.getAdminEmail());

			String hashedEnteredPassword = Hasher.hashingFunction(admin.getAdminPassword());
			String hashedPasswordFromDb = adminFromDb.getAdminPassword();
			if (hashedEnteredPassword.equals(hashedPasswordFromDb)) {
				return adminFromDb;
			}
		}
		return null;
	}

	public Admin getValidation(Admin admin) throws NoSuchAlgorithmException {
		if (getAdminByEmail(admin.getAdminEmail()) == null) {
			logger.error("Email ID : " + admin.getAdminEmail() + " not registered as Admin!");
			return null;
		} else {
				logger.info("Admin validation post request for Admin : "
						+ getAdminByEmail(admin.getAdminEmail()).getAdminId());
				return validatedAdmin(admin);			
		}
	}
}
