package com.onlinebookstore.service;

import com.onlinebookstore.model.OrdersModuleDto;

public interface OrderModuleService {

	String placeOrders(OrdersModuleDto ordersModuleDto);

	String returnOrders(OrdersModuleDto ordersModuleDto);

}
