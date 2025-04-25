package com.example.RegisterUser.RegisterUser.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;
import com.example.RegisterUser.RegisterUser.modal.responseMessage;
import com.example.RegisterUser.RegisterUser.userRegisterService.BooksModelService;
import com.example.RegisterUser.RegisterUser.utility.Constants;

@RestController
public class BooksController 
{
	private static Logger logger =LoggerFactory.getLogger(BooksController.class);
	@Autowired
	private BooksModelService booksModelService;
	
	@PostMapping("/createorupdatebooks")
	
	public ResponseEntity<responseMessage> booksCreated( @RequestBody BooksModule booksModule) {
		logger.info("booksCreated controller layer calling or started");  
	    try {
	        if (booksModule == null || booksModule.getTitle() == null || booksModule.getTitle().isBlank()) {
	       logger.debug("Recived userRegData: {} ",booksModule);
	       logger.warn("missing title and price booksmodule request"); 
	       logger.error("User booksmodule title and price  : Bad reg data "); 	
	            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "title and  price be empty!"));
	        }

	        BooksModule createdOrUpdatebook = booksModelService.createdOrUpdatebook(booksModule);
	        if (createdOrUpdatebook != null) {
	        	logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_CREATION_SUCCESS\" .");  	
	            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Books creation Successfully", createdOrUpdatebook));
	        } else {
	        	logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_CREATION_FAILED\" ."); 
	        	logger.info("booksCreated controller layer calling completed");  
	        	  logger.warn("User BooksModule service return null : createorupdatebooks failed"); 
	            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book creation failed"));
	        	
	        }
	    } catch (Exception e) {
	        logger.error("New user books creation process failed in Bookstore-DB . Exception:" +e.getMessage()); 	
	        return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book creation failed"));
	    }
	}
	@GetMapping("/getbooks/{id}")
	public ResponseEntity<responseMessage> getBookById(@PathVariable Long id)
	{
		logger.info("booksCreated controller layer calling or started");
		try
		{
			if(id==null||id==0)
			{
				logger.debug("Recived userRegData: {} ",id);
			       logger.warn("missing id booksmodule request"); 
			       logger.error("User booksmodule id  : Bad reg data "); 	
			            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "id should not be empty!"));
			}
			BooksModule books=booksModelService.booksFindById(id);
			if(books!=null)
			{
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_CREATION_SUCCESS\" .");  	
	            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Book retrived Successfully", books));
			}
			else
			{
				logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_CREATION_FAILED\" ."); 
	        	logger.info("booksCreated controller layer calling completed");  
	        	logger.warn("User BooksModule service return null : createorupdatebooks failed"); 
	            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book retrive failed"));
			}
		}
		catch(Exception e)
		{
			logger.error("New user books creation process failed in Bookstore-DB . Exception:" +e.getMessage()); 	
	        return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book retrive failed"));
		}
		
		
		
	}
	 @GetMapping("/getAllBooks")
	    public ResponseEntity<responseMessage> getByAllBooks() {
	    	
	        try {
	              List<BooksModule> booksList = booksModelService.getByAllBookss();
	            if (booksList != null) {
	                return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "getting All books successfuly", booksList));
	            } else {
	            	 return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "getting All books  failed"));
	            }
	        } catch (Exception e) {
	            return ResponseEntity.ok(new responseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "All books  getting failed"));
	        }
}
}

