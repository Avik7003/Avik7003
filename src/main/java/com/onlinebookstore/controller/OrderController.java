package com.onlinebookstore.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookstore.model.OrdersModuleDto;
import com.onlinebookstore.model.ResponseMessage;
import com.onlinebookstore.service.OrderModuleService;
import com.onlinebookstore.utility.Constants;


@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired private OrderModuleService orderModuleService;
	
	//	// Task Apply coupons and give discounts
	
	@PostMapping("/orderplace")
	public ResponseEntity<ResponseMessage> createBook(@RequestBody OrdersModuleDto ordersModuleDto) {
		try {
			if (ordersModuleDto == null || ordersModuleDto.getTitle() == null || ordersModuleDto.getTitle().isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"No books selected. Please select a book to proceed!"));
			}

			String result = orderModuleService.placeOrders(ordersModuleDto);

			if (result.toLowerCase().contains("successfully")) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, result));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, result));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Something went wrong: " + e.getMessage()));
		}
	}
	
	
//	{
//    "title": [],
//    "custmerId": 2
//}
	
//	{
//    "title": ["sdgsgsgsg"],
//    "custmerId": 2
//}

//	{
//  "title": ["Mahabhrat"],
//  "custmerId": 2
//}


	
//	{
//  "title": ["Mahabhrat","Bhagavad geetha""],
//  "custmerId": 2
//}



//
//	{
//	    "title": ["Mahabhrat","Bhagavad geetha" ,"Quran ","Harry potter "],
//	    "custmerId": 2
//	}
//

	
	

	
	@PostMapping("/returnorder")
	public ResponseEntity<ResponseMessage> orderByReturns(@RequestBody OrdersModuleDto ordersModuleDto) {
	
			String orders = orderModuleService.returnOrders(ordersModuleDto);

			if (orders!=null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,"order return successfully"));
			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "order failed"));
			}
		}
	}
	
//{
//    "title": ["Mahabhrat"],
//    "custmerId": 2
//}

//{
//    "statuscode": 201,
//    "status": "Success",
//    "message": "order return successfully",
//    "data": null,
//    "list": null
//}


