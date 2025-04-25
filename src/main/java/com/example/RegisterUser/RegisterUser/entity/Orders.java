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
@Table(name="orders")
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bookId")
	private Long bookId;
	
	@Column(name = "bookName")
	private String bookName;

	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "status")
	private Boolean status;

	@CreationTimestamp
	@Column(name = "createDate")
	private LocalDateTime createDate;

	@UpdateTimestamp
	@Column(name = "updateDate")
	private LocalDateTime updateDate;
}
