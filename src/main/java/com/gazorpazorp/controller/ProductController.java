package com.gazorpazorp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.Product;
import com.gazorpazorp.service.ProductService;

@RestController
@RequestMapping("/internal/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/{productId}")
	@PreAuthorize("#oauth2.hasScope('system')")
	public ResponseEntity getProductById (@PathVariable Long productId) throws Exception {
		return Optional.ofNullable(productService.getProductById(productId))
				.map(p -> new ResponseEntity<Product>(p, HttpStatus.OK))
				.orElseThrow(() -> new Exception("An unknown error has occured."));
	}
	
	@GetMapping
	@PreAuthorize("#oauth2.hasScope('system')")
	public ResponseEntity getProductsById(@RequestParam("productIds") String productIds) {
		return Optional.ofNullable(productService.getProductsById(productIds))
				.map(result -> new ResponseEntity<List<Product>>(result, HttpStatus.OK))
				.orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
	}

	
	
}
