package com.onlinebookstore.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadBooksExcelService {

	public void uploadFilesFromExcel(MultipartFile file) throws IOException;

}
