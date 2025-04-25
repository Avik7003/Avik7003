package com.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlinebookstore.entity.Files;


@Repository
public interface FilesRepository  extends JpaRepository<Files, Long> {
	
	
	

}
