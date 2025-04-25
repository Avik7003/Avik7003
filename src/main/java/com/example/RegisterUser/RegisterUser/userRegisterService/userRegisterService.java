package com.example.RegisterUser.RegisterUser.userRegisterService;

import com.example.RegisterUser.RegisterUser.entity.Register;
import com.example.RegisterUser.RegisterUser.modal.LoginModal;
import com.example.RegisterUser.RegisterUser.modal.UserRegData;

public interface userRegisterService {

	Register createUserWebService(UserRegData userRegData);

	Register createUserEmail(LoginModal loginModal);

}
