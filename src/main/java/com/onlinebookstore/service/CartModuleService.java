package com.onlinebookstore.service;

import com.onlinebookstore.entity.CartModule;

public interface CartModuleService {

	public CartModule addtoCartBook(Long custmerId, Long bookId, int quantity);

	public void deletecartfrombook(Long cartid);
}
