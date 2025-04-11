package com.tenpo.desafio.bl.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tenpo.desafio.dal.services.CallHistoryDataService;
import com.tenpo.desafio.domain.dto.CallHistoryDto;
import com.tenpo.desafio.domain.entities.CallHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class CallHistoryService {
	
	private final CallHistoryDataService dataService;
	
	private final ModelMapper mapper;
	
	public Page<CallHistoryDto> getPaginatedServiceLogs(int page, int size) {
		
		Page<CallHistory> response = dataService.getPaginatedServiceLogs(page, size);
		
		return response.map(history -> mapper.map(history, CallHistoryDto.class));
		
	}

}
