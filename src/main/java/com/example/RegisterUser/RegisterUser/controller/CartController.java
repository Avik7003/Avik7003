package com.example.RegisterUser.RegisterUser.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterUser.RegisterUser.entity.CartModule;
import com.example.RegisterUser.RegisterUser.modal.responseMessage;
import com.example.RegisterUser.RegisterUser.userRegisterService.CartModuleService;
import com.example.RegisterUser.RegisterUser.utility.Constants;


@RestController
public class CartController {

	@Autowired
	private CartModuleService cartModuleService;
	
	@PostMapping("/addcart")
	public ResponseEntity<responseMessage> createBook(@RequestParam Long customerId,
			@RequestParam Long bookId,

			@RequestParam int quantity) {

		CartModule cartBooks = cartModuleService.addtoCartBook(customerId, bookId, quantity);

		if (cartBooks != null) {

			return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
					"Books Add cart successfully", cartBooks));
		} else {
			return ResponseEntity
					.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "add cart failed"));
		}
	}
}
