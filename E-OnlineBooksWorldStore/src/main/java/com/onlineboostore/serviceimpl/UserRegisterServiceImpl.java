package com.onlineboostore.serviceimpl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineboostore.entity.UserRegister;
import com.onlineboostore.model.UserRegData;
import com.onlineboostore.repository.UserRegisterRepository;
import com.onlineboostore.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService{
	
	@Autowired
	private UserRegisterRepository userRegisterRepository;

	@Override
	public String createUserRegService(UserRegData userRegData) {
		UserRegister  user =null;
		try {
		user = new UserRegister();
		user.setFirstName(userRegData.getFirstName());
		user.setLastName(userRegData.getLastName());
		user.setEmail(userRegData.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRegData.getPassword().getBytes()));
		user.setContactId(userRegData.getContactId());
		userRegisterRepository.save(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "Registration Sucessfully";
	}

}
