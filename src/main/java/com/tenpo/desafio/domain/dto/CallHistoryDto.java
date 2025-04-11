package com.tenpo.desafio.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallHistoryDto {

	private Long id;
	
	private LocalDateTime date;
	
	private String endpoint;
	
	private String params;
	
	private String response;
}
