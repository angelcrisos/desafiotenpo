package com.tenpo.desafio.rest;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpo.desafio.bl.service.AsyncLogService;
import com.tenpo.desafio.bl.service.CalculatePercentageService;
import com.tenpo.desafio.domain.dto.CalculateRequestDto;
import com.tenpo.desafio.domain.dto.CallHistoryDto;
import com.tenpo.desafio.exceptions.CacheServiceException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@Validated
@Data
public class CalculatePercentageController {

	private final CalculatePercentageService calculateService;
	
	private final AsyncLogService logService;

	@Operation(summary = "Suma dos numeros y aplica porcentaje obtenido desde servicio externo")
	@GetMapping(value = "/calculatepercentage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getCalculatePercentage(@Valid @ModelAttribute CalculateRequestDto calculateRequest, HttpServletRequest request) {
		Double response = null;
		CallHistoryDto dto =  null;
		try {
			log.info("Ïnit getCalculatePercentage ", request.getQueryString());
			dto = CallHistoryDto.builder().endpoint(request.getRequestURL().toString())
					.date(LocalDateTime.now()).params(request.getQueryString()).build();
			response = calculateService.calculatePercentage(calculateRequest.getNum1(), calculateRequest.getNum2());
			dto.setResponse(response.toString());
			logService.save(dto);
		}catch(CacheServiceException e) {
			log.error(e.getMessage());
			dto.setResponse(e.getMessage());
			logService.save(dto);
			throw e;
		}
		log.info("End getCalculatePercentage response {} ", response );

		return ResponseEntity.ok(response);
	}

}
