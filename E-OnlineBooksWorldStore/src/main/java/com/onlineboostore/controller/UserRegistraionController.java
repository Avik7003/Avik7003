package com.onlineboostore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineboostore.model.UserRegData;
import com.onlineboostore.service.UserRegisterService;

@RestController
public class UserRegistraionController {

	@Autowired
	private UserRegisterService userRegisterService;

	@PostMapping("/userregister")
	public String createUserRegistration(@RequestBody UserRegData userRegData) {

		String userRegService = userRegisterService.createUserRegService(userRegData);

		return "User Registration Sucessfully";

	}

}
