package com.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookstore.entity.UserRegister;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegister, Long>{

public 	UserRegister findByEmail(String email);

}
