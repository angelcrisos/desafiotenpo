package com.tenpo.desafio.bl.service;

import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.tenpo.desafio.bl.client.PercentageClient;
import com.tenpo.desafio.bl.util.CalculateUtil;
import com.tenpo.desafio.exceptions.CacheServiceException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
@Data
public class CalculatePercentageService {

	private static final String PERCENTAGE = "percentage";

	private final PercentageClient client;
	
	private final Cache<String, Double> cache;

	
	public Double calculatePercentage(double numberOne, double numberTwo) {
		log.info("init calculatePercentage");
		Double porc = getPercentage();
		return CalculateUtil.calculateDynamicPercentage(numberOne, numberTwo, porc);
		
	}
	
	private Double getPercentage() {
		Double porc =null;
		try {
			 porc = client.getPercentage();
			 log.info("Porcentaje de servicio externo es {} ", porc);
			 cache.put(PERCENTAGE, porc);
			
		}catch( Exception e) {
			porc =  cache.getIfPresent(PERCENTAGE);
			if(porc == null) {
				throw new CacheServiceException("Error al obtener data desde cache");
			}	
		}
		
		return porc;
		
	}
	
}
