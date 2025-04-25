/*package com.onlinebookstore.serviceimpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlinebookstore.entity.BooksExcelFile;
import com.onlinebookstore.repository.BooksExcelFileRepository;
import com.onlinebookstore.service.UploadBooksExcelService;
import com.onlinebookstore.utility.Helper;

@Service
public class UploadBooksExcelServiceImpl implements UploadBooksExcelService{
	
	
	@Autowired
    private BooksExcelFileRepository booksExcelFileRepository;

	@Override
	public void uploadFilesFromExcel(MultipartFile file) throws IOException {
		
//		Helper hp = new Helper();
//		hp.convertExcelFileintoDB(null);
//		
//		
		List<BooksExcelFile> convertExcelFileintoDB = Helper.convertExcelFileintoDB(file.getInputStream());
		
		booksExcelFileRepository.saveAll(convertExcelFileintoDB);
		
	}

}*/
