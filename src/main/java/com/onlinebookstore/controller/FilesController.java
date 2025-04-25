package com.onlinebookstore.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onlinebookstore.entity.Files;
import com.onlinebookstore.repository.FilesRepository;

@RestController
public class FilesController {

	@Autowired
	private FilesRepository filesRepository;

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws Exception {

		Files fis = new Files();
		fis.setFileType(file.getContentType());
		fis.setFileName(file.getOriginalFilename());
		fis.setData(file.getBytes());
		filesRepository.save(fis);
		return ResponseEntity.ok("file insert successfully:" + file.getOriginalFilename());
	}

	@PostMapping("/uploadMutliple")
	public ResponseEntity<List<Object>> uploadMutlipleFiles(@RequestParam MultipartFile[] files){
		
		List<Object> response = Arrays.stream(files).map(s->{
			                   try {
			                	   return uploadFile(s);
			                   }catch (Exception e) {
			                	   return "upload fails...."+e.getMessage();
							}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}
	// 2 weeks or 3 weeks 
	
	@GetMapping("/getfiles/{id}")
	public ResponseEntity<byte []> getFile(@PathVariable Long id) throws Exception {
		
		Files files = filesRepository.findById(id).get();
		
		  return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFileName() + "\"").body(files.getData());
	}

}
