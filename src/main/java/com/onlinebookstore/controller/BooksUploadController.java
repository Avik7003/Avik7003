/*package com.onlinebookstore.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.model.ResponseMessage;
import com.onlinebookstore.service.UploadBooksExcelService;
import com.onlinebookstore.utility.Constants;
import com.onlinebookstore.utility.Helper;

@RestController
public class BooksUploadController {

	@Autowired
	UploadBooksExcelService uploadBooksExcelService;

	@PostMapping("/uploadExcel")
	public ResponseEntity<ResponseMessage> uploadExcelBooks(@RequestParam MultipartFile file) throws IOException {

		if (Helper.checkExcel(file)) {
			uploadBooksExcelService.uploadFilesFromExcel(file);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
					"Files uploaded successfully"));
		} else {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
					"Please upload the excel file!!", file.getOriginalFilename(), null));

		}

	}

}
*/