package com.example.RegisterUser.RegisterUser.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class responseMessage 
{
	
	

	private Integer statuscode;
	private String status;
	private String message;
	private Object data;
	
	public responseMessage(Integer statuscode, String status, String message) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
	}
	public responseMessage(Integer statuscode, String status, String message, Object data) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
