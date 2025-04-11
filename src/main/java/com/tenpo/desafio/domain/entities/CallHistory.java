package com.tenpo.desafio.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity(name = "CALL_HISTORY") 
public class CallHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private LocalDateTime date;
	
	@Column  (nullable = false)
	private String endpoint;
	
	@Column
	private String params;
	
	@Column  (nullable = false)
	private String response;
	
	

}
