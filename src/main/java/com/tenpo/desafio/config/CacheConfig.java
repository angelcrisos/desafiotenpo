package com.tenpo.desafio.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CacheConfig {

	@Bean
	public Cache<String, Double> cache() {
		return Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(1000).build();
	}
}