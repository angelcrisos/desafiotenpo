package com.tenpo.desafio.rest;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/mock-api")
public class PercentageMockController {

	@Operation(summary = "Servicio mock utilizado para el desafio, puede devolver porcentaje o error")
	 @GetMapping("/percentage")
	public Double getPercentage() {
		if (new Random().nextBoolean()) {
			throw new RuntimeException("Error al obtener porcentaje");
		}

		return Double.valueOf(new Random().nextInt(100));
	}

}
