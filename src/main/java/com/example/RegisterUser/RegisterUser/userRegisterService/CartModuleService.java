package com.example.RegisterUser.RegisterUser.userRegisterService;

import com.example.RegisterUser.RegisterUser.entity.CartModule;

public interface CartModuleService {

	CartModule addtoCartBook(Long customerId, Long bookId, int quantity);

}
