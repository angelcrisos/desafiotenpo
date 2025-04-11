package com.tenpo.desafio.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;

public class PercentageFeignClientConfiguration {
	
	@Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

}
