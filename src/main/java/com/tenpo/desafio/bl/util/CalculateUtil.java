package com.tenpo.desafio.bl.util;

public class CalculateUtil {
	
	private CalculateUtil() {
		
	}
	
	public static Double calculateDynamicPercentage(Double numberOne, Double numberTwo, Double percentage) {
		Double sum = numberOne + numberTwo;
		Double extra = sum * (percentage / 100);
		return sum + extra;
	}

}
