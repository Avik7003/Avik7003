package com.onlinebookstore.serviceimpl;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlinebookstore.entity.Files;
import com.onlinebookstore.entity.UserRegister;
import com.onlinebookstore.model.LoginModel;
import com.onlinebookstore.model.UserRegData;
import com.onlinebookstore.repository.FilesRepository;
import com.onlinebookstore.repository.UserRegisterRepository;
import com.onlinebookstore.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	private UserRegisterRepository userRegisterRepository;

	@Autowired
	private FilesRepository filesRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegisterServiceImpl.class);

	@Override
	public UserRegister createUserRegService(UserRegData userRegData) {
		logger.info("Registartion service layer calling or started");
		UserRegister user = null;
		try {
			user = new UserRegister();
			user.setFirstName(userRegData.getFirstName());
			user.setLastName(userRegData.getLastName());
			user.setEmail(userRegData.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRegData.getPassword().getBytes()));
			user.setContactId(userRegData.getContactId());
			userRegisterRepository.save(user);

		} catch (Exception e) {
		    logger.error("New user creation process failed in Bookstore-DB . Exception:" +e.getMessage()); 
			e.printStackTrace();
		}
		logger.info("Registartion service layer calling ended....");
		return user;
	}


	@Override
	public UserRegister createLoginUser(LoginModel loginModel) {

		// find the email exist in database
		UserRegister userEmail = userRegisterRepository.findByEmail(loginModel.getEmail());

		// verify condtion
		if (userEmail != null) {

			// decodoing the password
			String decode = new String(Base64.getDecoder().decode(userEmail.getPassword()));

			// chech the condtions password is match or not
			if (decode.equals(loginModel.getPassword())) {

				return userEmail;
			}
		}
		return null;
	}


	@Override
	public UserRegister createUserRegServicess(UserRegData userRegData, MultipartFile[] files) {
		UserRegister user = null;
		try {
			user = new UserRegister();
			user.setFirstName(userRegData.getFirstName());
			user.setLastName(userRegData.getLastName());
			user.setEmail(userRegData.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRegData.getPassword().getBytes()));
			user.setContactId(userRegData.getContactId());
			userRegisterRepository.save(user);
			
			if(files!=null && files.length>0) {
				for (MultipartFile multipartFile : files) {
					Files fis = new Files();
					fis.setFileType(multipartFile.getContentType());
					fis.setFileName(multipartFile.getOriginalFilename());
					fis.setData(multipartFile.getBytes());
					filesRepository.save(fis);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
		
		
	
}
