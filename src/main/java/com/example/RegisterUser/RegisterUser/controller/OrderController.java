package com.example.RegisterUser.RegisterUser.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterUser.RegisterUser.modal.OrderModuleDTO;
import com.example.RegisterUser.RegisterUser.modal.responseMessage;
import com.example.RegisterUser.RegisterUser.userRegisterService.OrderModuleService;
import com.example.RegisterUser.RegisterUser.utility.Constants;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderModuleService OrderModuleService;
	
	@PostMapping("/orderplace")
	public ResponseEntity<responseMessage> createBook(@RequestBody OrderModuleDTO orderModuleDTO)
	{
		try
		{
			if(orderModuleDTO==null||orderModuleDTO.getTitle()==null|| orderModuleDTO.getTitle().isEmpty())
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"No books selected please select a book to procced..!"));
			}
			String result=OrderModuleService.placeOrders(orderModuleDTO);
			if(result.toLowerCase().contains("successfully"))
			{
				return  ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,result));
			}
			else
			{
				return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,result));
			}
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"something went wrong :",e.getMessage()));
		}
		
		
	}
}
