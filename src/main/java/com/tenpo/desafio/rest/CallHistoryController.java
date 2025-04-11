package com.tenpo.desafio.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpo.desafio.bl.service.CallHistoryService;
import com.tenpo.desafio.domain.dto.CallHistoryDto;
import com.tenpo.desafio.domain.dto.CallHistoryRequestDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@Data
public class CallHistoryController {
	
	private final CallHistoryService service;
	private final PagedResourcesAssembler<CallHistoryDto> assembler;

	@Operation(summary = "Retorna historial de llamadas")
	@GetMapping(value = "/call-history", produces = MediaType.APPLICATION_JSON_VALUE)
	public PagedModel<EntityModel<CallHistoryDto>> searchRefundStatus22(@Valid @ModelAttribute CallHistoryRequestDto dto) {

		Page<CallHistoryDto> page = service.getPaginatedServiceLogs(dto.getPage(), dto.getSize());
	    return assembler.toModel(page);
	}

}
