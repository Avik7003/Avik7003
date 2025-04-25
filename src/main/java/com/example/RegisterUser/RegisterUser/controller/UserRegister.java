package com.example.RegisterUser.RegisterUser.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterUser.RegisterUser.entity.Register;
import com.example.RegisterUser.RegisterUser.modal.LoginModal;
import com.example.RegisterUser.RegisterUser.modal.UserRegData;
import com.example.RegisterUser.RegisterUser.modal.responseMessage;
import com.example.RegisterUser.RegisterUser.userRegisterService.userRegisterService;
import com.example.RegisterUser.RegisterUser.utility.Constants;

@RestController
public class UserRegister 
{
	@Autowired
	private userRegisterService userRegisterService;
	
	@PostMapping("/registeruser")
	public ResponseEntity<responseMessage> createUser(@RequestBody UserRegData userRegData)
	{
		try
		{
			if(userRegData==null||userRegData.getEmail()==null||userRegData.getEmail().isBlank()||userRegData.getPassword()==null||userRegData.getPassword().isBlank())
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Registration not successfull"));
			}
			Register userWebService=userRegisterService.createUserWebService(userRegData);
			if(userWebService!=null)
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_ACCEPTED,Constants.SUCCESS,"Registration successfull",userWebService));
			}
			else
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Registration not successfull",userWebService));
			}
			
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Registration not successfull"));
		}
	
	
	}
	@PostMapping("/loginuser")
	public ResponseEntity<responseMessage> loginUser(@RequestBody LoginModal loginModal)
	{
		try
		{
			if(loginModal==null||loginModal.getEmail()==null||loginModal.getEmail().isBlank()||loginModal.getPassword()==null||loginModal.getPassword().isBlank())
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email and password fields are empty"));
			}
			Register loginUser=userRegisterService.createUserEmail(loginModal);
			if(loginUser!=null)
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_ACCEPTED,Constants.SUCCESS,"successfuly logged in",loginUser));
			}
			else
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"email and password are wrong",loginUser));
			}
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"invalid credentials"));
		}
			
		
	}
		
}
