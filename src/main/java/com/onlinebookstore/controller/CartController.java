package com.onlinebookstore.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookstore.entity.CartModule;
import com.onlinebookstore.model.ResponseMessage;
import com.onlinebookstore.service.CartModuleService;
import com.onlinebookstore.utility.Constants;


// take one sample zomato controller add to cart and remove the cart 
@RestController
public class CartController {

	@Autowired
	private CartModuleService cartModuleService;

	@PostMapping("/addtocart")
	public ResponseEntity<ResponseMessage> createBook(@RequestParam Long custmerId, @RequestParam Long bookId,

			@RequestParam int quantity) {

		CartModule cartBooks = cartModuleService.addtoCartBook(custmerId, bookId, quantity);

		if (cartBooks != null) {

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
					"Books Add cart successfully", cartBooks));
		} else {
			return ResponseEntity
					.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "add cart failed"));
		}
	}
	
	@DeleteMapping("/removecart/{cartid}")
	public ResponseEntity<ResponseMessage> createBook(@PathVariable Long cartid) {

		 cartModuleService.deletecartfrombook(cartid);

			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,"delete book from cart successfully"));
		}
	}
