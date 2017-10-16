package com.gazorpazorp.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dataset {
	public Dataset() {}
	
	@JsonProperty("id")
	private int id;
	
	
	@JsonProperty("total_products")
	private Long totalProducts;
	@JsonProperty("total_stores")
	private Long totalStores;
	
	@JsonProperty("created_at")
	private Date createdAt;
	
	@JsonProperty("product_ids")
	private List<Long> productIds;
	@JsonProperty("store_ids")
	private List<Long> storeIds;
	
	@JsonProperty("added_product_ids")
	private List<Long> addedProductIds;
	@JsonProperty("added_store_ids")
	private List<Long> addedStoreIds;
	@JsonProperty("removed_product_ids")
	private List<Long> removedProductIds;
	@JsonProperty("removed_store_ids")
	private List<Long> removedStoreIds;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(Long totalProducts) {
		this.totalProducts = totalProducts;
	}
	public Long getTotalStores() {
		return totalStores;
	}
	public void setTotalStores(Long totalStores) {
		this.totalStores = totalStores;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public List<Long> getProductIds() {
		return productIds;
	}
	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	public List<Long> getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
	}
	public List<Long> getAddedProductIds() {
		return addedProductIds;
	}
	public void setAddedProductIds(List<Long> addedProductIds) {
		this.addedProductIds = addedProductIds;
	}
	public List<Long> getAddedStoreIds() {
		return addedStoreIds;
	}
	public void setAddedStoreIds(List<Long> addedStoreIds) {
		this.addedStoreIds = addedStoreIds;
	}
	public List<Long> getRemovedProductIds() {
		return removedProductIds;
	}
	public void setRemovedProductIds(List<Long> removedProductIds) {
		this.removedProductIds = removedProductIds;
	}
	public List<Long> getRemovedStoreIds() {
		return removedStoreIds;
	}
	public void setRemovedStoreIds(List<Long> removedStoreIds) {
		this.removedStoreIds = removedStoreIds;
	}
	
	
}
