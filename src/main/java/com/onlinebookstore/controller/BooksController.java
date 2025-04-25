package com.onlinebookstore.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.entity.UserRegister;
import com.onlinebookstore.model.ResponseMessage;
import com.onlinebookstore.service.BooksModuleService;
import com.onlinebookstore.utility.Constants;

//import io.swagger.annotations.ApiParam;

@RestController
public class BooksController {
	
	private static Logger logger = LoggerFactory.getLogger(BooksController.class);
	
	
	@Autowired
	private BooksModuleService booksModuleService;
	
	
	  @PostMapping("/createorupdatebooks")
	    public ResponseEntity<ResponseMessage> booksCreated( @RequestBody BooksModule booksModule) {
	    	logger.info("booksCreated controller layer calling or started");  
	        try {
	            if (booksModule == null || booksModule.getTitle() == null || booksModule.getTitle().isBlank()) {
	           logger.debug("Recived userRegData: {} ",booksModule);
	           logger.warn("missing title and price booksmodule request"); 
	           logger.error("User booksmodule title and price  : Bad reg data "); 	
	                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "title and  price be empty!"));
	            }

	            BooksModule createdOrUpdatebook = booksModuleService.createdOrUpdatebook(booksModule);
	            if (createdOrUpdatebook != null) {
	            	logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_CREATION_SUCCESS\" .");  	
	                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Books creation Successfully", createdOrUpdatebook));
	            } else {
	            	logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_CREATION_FAILED\" ."); 
	            	logger.info("booksCreated controller layer calling completed");  
	            	  logger.warn("User BooksModule service return null : createorupdatebooks failed"); 
	                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book creation failed"));
	            	
	            }
	        } catch (Exception e) {
	            logger.error("New user books creation process failed in Bookstore-DB . Exception:" +e.getMessage()); 	
	            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book creation failed"));
	        }
	    }
	  
	  
	    @GetMapping("/getByBookId/{bookId}")
	    public ResponseEntity<ResponseMessage> getByBooksId(@PathVariable Long bookId) {
	        try {
	            if (bookId == null || bookId==0) {
	                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "book Id cannot be empty!"));
	            }

	             BooksModule bookId2 = booksModuleService.getBookId(bookId);
	            if (bookId2 != null) {
	                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Book Id successfuly retrieved ", bookId2));
	            } else {
	            	 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "BookId not found failed"));
	            }
	        } catch (Exception e) {
	            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "BookId getting failed"));
	        }
	    }
	    
	    @GetMapping("/getAllBooks")
	    public ResponseEntity<ResponseMessage> getByAllBooks() {
	    	
	        try {
	              List<BooksModule> booksList = booksModuleService.getByAllBookss();
	            if (booksList != null) {
	                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "getting All books successfuly", booksList));
	            } else {
	            	 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "getting All books  failed"));
	            }
	        } catch (Exception e) {
	            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "All books  getting failed"));
	        }
	    }
	
	
	

}
