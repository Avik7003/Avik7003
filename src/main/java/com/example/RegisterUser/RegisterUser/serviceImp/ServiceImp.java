package com.example.RegisterUser.RegisterUser.serviceImp;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegisterUser.RegisterUser.entity.Register;
import com.example.RegisterUser.RegisterUser.modal.LoginModal;
import com.example.RegisterUser.RegisterUser.modal.UserRegData;
import com.example.RegisterUser.RegisterUser.repo.userRepository;
import com.example.RegisterUser.RegisterUser.userRegisterService.userRegisterService;
@Service
public class ServiceImp implements userRegisterService {

	@Autowired
	private userRepository userRepository;
	
	@Override
	public Register createUserWebService(UserRegData userRegData) {
		Register user=null;
		try
		{
			user=new Register();
			user.setFirstname(userRegData.getFirstname());
			user.setLastname(userRegData.getLastname());
			user.setEmail(userRegData.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRegData.getPassword().getBytes()));
			user.setContact(userRegData.getContact());
			userRepository.save(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Register createUserEmail(LoginModal loginModal) {
		Register email=userRepository.findByEmail(loginModal.getEmail());
		if(email!=null)
		{
			String decode = new String(Base64.getDecoder().decode(email.getPassword()));
			if(decode.equals(loginModal.getPassword()))
			{
				return email;
			}
		}
		return null;
	}

}
