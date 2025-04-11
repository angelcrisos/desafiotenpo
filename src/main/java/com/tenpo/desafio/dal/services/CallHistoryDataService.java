package com.tenpo.desafio.dal.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tenpo.desafio.dal.repository.CallHistoryRepository;
import com.tenpo.desafio.domain.entities.CallHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class CallHistoryDataService {
	
	private final CallHistoryRepository repository;
	
	public CallHistory save(CallHistory callHistory) {
		return this.repository.save(callHistory);
	}
	
	public Page<CallHistory> getPaginatedServiceLogs(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<CallHistory> logs = this.repository.findAll(pageable);
		return logs;
	}

}
