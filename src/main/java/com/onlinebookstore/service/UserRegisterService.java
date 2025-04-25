package com.onlinebookstore.service;

import org.springframework.web.multipart.MultipartFile;

import com.onlinebookstore.entity.UserRegister;
import com.onlinebookstore.model.LoginModel;
import com.onlinebookstore.model.UserRegData;

public interface UserRegisterService {

	public UserRegister createUserRegService(UserRegData userRegData);

	public UserRegister createLoginUser(LoginModel loginModel);

	public UserRegister createUserRegServicess(UserRegData userRegData, MultipartFile[] files);


	

}
