package com.example.RegisterUser.RegisterUser.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Registration_form")
public class Register 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	@Column(name="email_id")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="contact")
	private String contact;
	@CreationTimestamp
	@Column(name="create_date")
	private LocalDateTime createdate;
	@UpdateTimestamp
	@Column(name="update_date")
	private LocalDateTime updatedate;
	@Column(name = "prime", columnDefinition = "TINYINT(1)")
	private boolean prime;
}
