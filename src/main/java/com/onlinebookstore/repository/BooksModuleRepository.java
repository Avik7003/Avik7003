package com.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookstore.entity.BooksModule;

@Repository
public interface BooksModuleRepository extends JpaRepository<BooksModule, Long>{

}

//cntrl+shift+o