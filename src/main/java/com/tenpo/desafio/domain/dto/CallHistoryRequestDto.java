package com.tenpo.desafio.domain.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CallHistoryRequestDto {
	
	@NotNull(message = "Campo page es obligatorio")
	@DecimalMin( value = "0", inclusive = true, message = "Campo page debe ser mayor o igual a cero")
	@DecimalMax( value = "999", inclusive = true, message = "Campo page debe ser menor o igual a 999")
	private Integer page;
	
	@NotNull(message = "Campo size es obligatorio")
	@Positive(message = "Campo size debe ser mayor a CERO")
	@DecimalMin( value = "1", inclusive = true, message = "Campo size debe ser mayor o igual a uno")
	@DecimalMax( value = "100000", inclusive = true, message = "Campo page debe ser menor o igual a size")
	@Digits(integer=6, fraction =0, message = "Campo size debe ser Entero hasta 6 Digitos")
	private Integer size;
	

}
