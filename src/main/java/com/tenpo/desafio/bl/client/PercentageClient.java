package com.tenpo.desafio.bl.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tenpo.desafio.config.PercentageFeignClientConfiguration;

@FeignClient(name = "percentageClient",  url = "${spring.application.endpoint.client.url}", configuration = PercentageFeignClientConfiguration.class)
public interface PercentageClient {
	
    @RequestMapping(method = RequestMethod.GET, value = "/percentage", produces = MediaType.APPLICATION_JSON_VALUE)
    Double getPercentage();

}
