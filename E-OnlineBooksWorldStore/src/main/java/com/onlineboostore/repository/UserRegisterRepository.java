package com.onlineboostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineboostore.entity.UserRegister;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegister, Long>{

}
