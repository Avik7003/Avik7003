package com.onlinebookstore.controller;

import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebookstore.entity.UserRegister;
import com.onlinebookstore.model.LoginModel;
import com.onlinebookstore.model.ResponseMessage;
import com.onlinebookstore.model.UserRegData;
import com.onlinebookstore.service.UserRegisterService;
import com.onlinebookstore.utility.Constants;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;*/

//@Api(value = "User Registration and Login Operations", tags = {"User Operations"})
@RestController
public class UserRegistraionController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegistraionController.class);

    @Autowired
    private UserRegisterService userRegisterService;

   
    /*@ApiOperation(value = "User Registration", notes = "Register a new user", response = ResponseMessage.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "User Registered Successfully", response = ResponseMessage.class),
        @ApiResponse(code = 400, message = "User Registration Failed", response = ResponseMessage.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseMessage.class)
    })*/
    @PostMapping("/userregister")
    public ResponseEntity<ResponseMessage> createUserRegistration( @RequestBody UserRegData userRegData) {
    	logger.info("Registration controller layer calling or started");  
        try {
            // Check for empty email or password
            if (userRegData == null || userRegData.getEmail() == null || userRegData.getEmail().isBlank() 
                || userRegData.getPassword() == null || userRegData.getPassword().isBlank()) {
           logger.debug("Recived userRegData: {} ",userRegData);
           logger.warn("missing email and password registration request"); 
           logger.error("User Registration email or password missing : Bad reg data "); 	
                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Email and password cannot be empty!"));
            }

            // Call the service to register the user
            UserRegister userRegService = userRegisterService.createUserRegService(userRegData);
            if (userRegService != null) {
            	logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_REGISTRATION_CREATION_SUCCESS\" .");  	
                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Registered Successfully", userRegService));
            } else {
            	logger.info("Message return eco-system = \"BOOKSTORE_ONLINE_REGISTRATION_CREATION_FAILED\" ."); 
            	logger.info("Registration controller layer calling completed");  
            	  logger.warn("User Registration service return null : registration failed"); 
                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Registration Failed"));
            	
            }
        } catch (Exception e) {
            logger.error("New user creation process failed in Bookstore-DB . Exception:" +e.getMessage()); 	
            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Registration failed"));
        }
    }

    /*@ApiOperation(value = "User Login", notes = "Authenticate a user and login", response = ResponseMessage.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "User Logged in Successfully", response = ResponseMessage.class),
        @ApiResponse(code = 400, message = "Invalid email or password", response = ResponseMessage.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseMessage.class)
    })*/
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> createLogin( @RequestBody LoginModel loginModel) {
        
        try {
            // Check for empty email or password
            if (loginModel == null || loginModel.getEmail() == null || loginModel.getEmail().isBlank() 
                || loginModel.getPassword() == null || loginModel.getPassword().isBlank()) {
                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Email and password cannot be empty!"));
            }

            // Call the service to check login credentials
            UserRegister userLogin = userRegisterService.createLoginUser(loginModel);
            if (userLogin != null) {
                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Logged in Successfully. Welcome to the E-commerce Online Book Store!", userLogin));
            } else {
                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Invalid email or password"));
            }
        } catch (Exception e) {
        	
            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Bad Credentials"));
        }
    }
    
//    @PostMapping("/userregister")
//    public ResponseEntity<ResponseMessage> createUserRegistration(@ApiParam(value = "User Registration Data", required = true)
//            @RequestParam String userRegDataJson,
//    		@RequestParam MultipartFile[] files) {
//        
//        try {
//
//            // Call the service to register the user
//        	UserRegData userRegData = new ObjectMapper().readValue(userRegDataJson, UserRegData.class);
//            UserRegister userRegService = userRegisterService.createUserRegServicess(userRegData ,files);
//            if (userRegService != null) {
//                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Registered Successfully", userRegService));
//            } else {
//                return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Registration Failed"));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Registration failed"));
//        }
//    }
   
}
