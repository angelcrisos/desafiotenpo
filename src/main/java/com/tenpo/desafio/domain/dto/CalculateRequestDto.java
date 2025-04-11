package com.tenpo.desafio.domain.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculateRequestDto {
	
	@NotNull(message = "Campo num1 es obligatorio")
	@Positive(message = "Campo num1 debe ser mayor a CERO")
	@Digits(integer=6, fraction =2, message = "Campo num1 debe ser Entero hasta 6 Digitos y 2 decimales")
	private Double num1;
	
	@NotNull(message = "Campo num2 es obligatorio")
	@Positive(message = "Campo num2 debe ser mayor a CERO")
	@Digits(integer=6, fraction =2, message = "Campo num2 debe ser Entero hasta 6 Digitos y 2 decimales")
	private Double num2;
	

}
