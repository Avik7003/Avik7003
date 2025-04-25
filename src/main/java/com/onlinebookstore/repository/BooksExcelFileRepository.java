package com.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookstore.entity.BooksExcelFile;

@Repository
public interface BooksExcelFileRepository extends JpaRepository<BooksExcelFile, Long>{

}
