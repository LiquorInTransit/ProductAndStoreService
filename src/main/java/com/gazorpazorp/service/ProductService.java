package com.gazorpazorp.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.LCBOFeignClient;
import com.gazorpazorp.model.Product;
import com.gazorpazorp.repository.ProductRestRepository;

@Service
public class ProductService {

	@Autowired
	ProductRestRepository productRepo;
	
	@Autowired
	LCBOFeignClient lcboClient;
	
	//TODO: These will not contact the lcboapi. they will contact the repo.
	String key = "MDo1NDQwN2RjYy0wMDhkLTExZTctYWEwNy0yMzI4NjgxOTRjOWU6V2hSaDdoOXBVbjFjTU80cUtBZlpxRkI4UlJDVWcxRWlBUWZZ";
	
	public Product getProductById(Long productId) {
		return productRepo.findById(productId).orElse(null);
//		Product product = lcboClient.getProductById(productId).getResult();
//		replaceSadCharacters(product.getName());
//		replaceSadCharacters(product.getDescription());
//		replaceSadCharacters(product.getTastingNote());
//		replaceSadCharacters(product.getSecondaryCategory());
//		replaceSadCharacters(product.getProducerName());
//		replaceSadCharacters(product.getServingSuggestion());
//		return product;
	}
	
	public void replaceSadCharactersOnProduct(Product product) {
		replaceSadCharacters(product.getName());
		replaceSadCharacters(product.getDescription());
		replaceSadCharacters(product.getTastingNote());
		replaceSadCharacters(product.getSecondaryCategory());
		replaceSadCharacters(product.getProducerName());
		replaceSadCharacters(product.getServingSuggestion());
	}	
	private void replaceSadCharacters(String str) {
		if (str == null)
			return;
		str.replace("\\", "");    
		str.replace("u005c", ""); 
		str.replace("u00e9", "é");
		str.replace("u000d", "");
		str.replace("u00bd", "½");
		str.replace("u00e2", "â");
		str.replace("u00e7", "ç");
		str.replace("u00e8", "è");
		str.replace("u00fb", "û");
		str.replace("u00f1", "ñ");
		str.replace("u00f4", "ô");
	}
	
	public List<Product> getProductsById(String productIds) {
		return productRepo.findAllById(Arrays.asList(productIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList()));
		//return Arrays.asList(productIds.split(",")).stream().map(id -> getProductById(Long.parseLong(id))).collect(Collectors.toList());
	}
	
	public List<Product> getLCBOProductsById(String productIds) {
		return Arrays.asList(productIds.split(",")).stream().map(id -> lcboClient.getProductById(Long.parseLong(id)).getResult()).collect(Collectors.toList()).stream().collect(Collectors.toList());
	}
}
