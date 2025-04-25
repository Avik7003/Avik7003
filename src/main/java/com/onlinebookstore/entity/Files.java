package com.onlinebookstore.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class Files {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "filename")
	private String fileName; 
	
	@Column(name = "filetype")
	private String fileType; 
	
	@Column(columnDefinition = "longblob")
	@Lob
	private byte[] data; 
	
	@CreationTimestamp
	@Column(name = "createDate")
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name = "updateDate")
	private LocalDateTime updateDate;
	
	
	

}
