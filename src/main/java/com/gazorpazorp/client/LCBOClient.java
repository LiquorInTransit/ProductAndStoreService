//package com.gazorpazorp.client;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.gazorpazorp.model.Product;
//import com.gazorpazorp.model.ProductResult;
//
//@Service
//public class LCBOClient {
//	
//	String key = "MDo1NDQwN2RjYy0wMDhkLTExZTctYWEwNy0yMzI4NjgxOTRjOWU6V2hSaDdoOXBVbjFjTU80cUtBZlpxRkI4UlJDVWcxRWlBUWZZ";
//	
//	//this shit is working and its a pain in my fucking asshole
//	public Product getProductById(Long productId) {
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(headers.AUTHORIZATION, "Token "+key);
//		HttpEntity entity = new HttpEntity(headers);
//		ProductResult result = restTemplate.exchange("https://www.lcboapi.com/products/"+productId, HttpMethod.GET, entity, ProductResult.class).getBody();
//		return result.getResult();
//	}
//	
//	
//}
