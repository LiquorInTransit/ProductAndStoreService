package com.gazorpazorp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetResult {

	public DatasetResult() {}
	@JsonProperty("result")
	private Dataset result;
	public Dataset getResult() {
		return result;
	}
	public void setResult(Dataset result) {
		this.result = result;
	}
	
	
}
