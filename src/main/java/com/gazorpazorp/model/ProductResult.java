package com.gazorpazorp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResult {
	public ProductResult() {}
	
	@JsonProperty("result")
	private Product result;

	public Product getResult() {
		return result;
	}

	public void setResult(Product result) {
		this.result = result;
	}
	
	
}
