package com.onlinebookstore.exceptions;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onlinebookstore.model.ErrorResponse;
import com.onlinebookstore.utility.Constants;

@RestControllerAdvice
public class RestGlobalException {

	@ExceptionHandler(CustmerIdNotFoundException.class)
	public ResponseEntity<Object> handleCustmerExcpetions(CustmerIdNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add("Error : Custmer ID not found");
		details.add("Detailed Message" + ex.getLocalizedMessage());
		details.add("Timestamp:" + System.currentTimeMillis());
		ErrorResponse er = new ErrorResponse(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "NOTEXIST", details);
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}
	
	
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<Object> handleBooksExcpetions(BookIdNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add("Error : Book ID not found");
		details.add("Detailed Message" + ex.getLocalizedMessage());
		details.add("Timestamp:" + System.currentTimeMillis());
		ErrorResponse er = new ErrorResponse(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "NOTEXIST", details);
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}

	

}
