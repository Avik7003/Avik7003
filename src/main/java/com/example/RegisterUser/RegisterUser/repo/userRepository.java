package com.example.RegisterUser.RegisterUser.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RegisterUser.RegisterUser.entity.Register;

//import com.example.RegisterUser.RegisterUser.controller.UserRegister;

@Repository
public interface userRepository extends JpaRepository<Register,Long> {

	Register findByEmail(String email);

	

}
