package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gazorpazorp.model.ProductResult;

import feign.Headers;

@FeignClient(name="lcbo-client"/*, url="${lcbo-client.url}"*/) //Name can be custom name defined in bootstrap.yml, or the name of a service registered with eureka
public interface LCBOFeignClient {
	
	@GetMapping(value="/products/{id}", consumes = "application/json")
	@Headers(value = { "Authorization: Token ${lcbo-client.key}", "Content-Type: application/json"})
	ProductResult getProductById(@PathVariable("id") Long id);
}

