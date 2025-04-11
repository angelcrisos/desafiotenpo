package com.tenpo.desafio.bl.service;

import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tenpo.desafio.dal.services.CallHistoryDataService;
import com.tenpo.desafio.domain.dto.CallHistoryDto;
import com.tenpo.desafio.domain.entities.CallHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@AllArgsConstructor
@Data
public class AsyncLogService {
	
	private final CallHistoryDataService dataService;
	
	@Async
	public void save(CallHistoryDto logService) {
		log.debug("Ïnit save {}", logService);
		CallHistory entity = new CallHistory();
		BeanUtils.copyProperties(logService, entity);
		var response = dataService.save(entity);
		BeanUtils.copyProperties(response, logService);
		log.debug("End save");
		
	}

}
