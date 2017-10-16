package com.gazorpazorp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreListResult {

	public StoreListResult() {}
	
	@JsonProperty("result")
	public List<Store> result;

	public List<Store> getResult() {
		return result;
	}
	public void setResult(List<Store> result) {
		this.result = result;
	}
	
	
}
