package com.gazorpazorp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="product")
public class Product {
	///////////////////////////////////////
	//LCBO-API Stored Properties
	///////////////////////////////////////
	@Id
	@JsonProperty("id")
	private Long id;
	@JsonProperty("is_dead")
	private Boolean isDead;
	@JsonProperty("name")
	private String name;
	@Column(length=500)
	@JsonProperty("tags")
	private String tags;
	@JsonProperty("is_discontinued")
	private Boolean isDiscontinued;
	@JsonProperty("price_in_cents")
	private Long priceInCents;
	@JsonProperty("stock_type")
	private String stockType;
	@JsonProperty("primary_category")
	private String primaryCategory;
	@JsonProperty("secondary_category")
	private String secondaryCategory;
	@JsonProperty("origin")
	private String origin;
	//TODO: Readd these
	@Column(name="package")
	@JsonProperty("package")
	private String packageType;
	@JsonProperty("package_unit_type")
	private String packageUnitType;
	@JsonProperty("package_unit_volume_in_milliliters")
	private Long packageUnitVolumeInMilliliters;
	@JsonProperty("total_package_units")
	private Long totalPackageUnits;
	@JsonProperty("volume_in_milliliters")
	private Long volumeInMilliliters;
	@JsonProperty("alcohol_content")
	private Long alcoholContent;
	@JsonProperty("producer_name")
	private String producerName;
	@JsonProperty("released_on")
	private String releasedOn;
	@JsonProperty("is_seasonal")
	private Boolean isSeasonal;
	@JsonProperty("is_vqa")
	private Boolean isVqa;
	@JsonProperty("is_ocb")
	private Boolean isOcb;
	@JsonProperty("is_kosher")
	private Boolean isKosher;
	@Column(length=2000)
	@JsonProperty("description")
	private String description;
	@Column(length=2000)
	@JsonProperty("serving_suggestion")
	private String servingSuggestion;
	@Column(length=2000)
	@JsonProperty("tasting_note")
	private String tastingNote;
	@JsonProperty("updated_at")
	private String updatedAt;
	@Column(length=500)
	@JsonProperty("image_thumb_url")
	private String imageThumbUrl;
	@Column(length=500)
	@JsonProperty("image_url")
	private String imageUrl;
	@JsonProperty("varietal")
	private String varietal;
	@JsonProperty("style")
	private String style;
	@JsonProperty("tertiary_category")
	private String tertiaryCategory;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDead() {
		return isDead;
	}

	public void setIsDead(Boolean isDead) {
		this.isDead = isDead;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Boolean getIsDiscontinued() {
		return isDiscontinued;
	}

	public void setIsDiscontinued(Boolean isDiscontinued) {
		this.isDiscontinued = isDiscontinued;
	}

	public Long getPriceInCents() {
		return (long) (priceInCents*1.029);
	}

	public void setPriceInCents(Long priceInCents) {
		this.priceInCents = priceInCents;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getPrimaryCategory() {
		return primaryCategory;
	}

	public void setPrimaryCategory(String primaryCategory) {
		this.primaryCategory = primaryCategory;
	}

	public String getSecondaryCategory() {
		return secondaryCategory;
	}

	public void setSecondaryCategory(String secondaryCategory) {
		this.secondaryCategory = secondaryCategory;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}	

	//TODO: Readd these
	@Column(name="package")
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageUnitType() {
		return packageUnitType;
	}

	public void setPackageUnitType(String packageUnitType) {
		this.packageUnitType = packageUnitType;
	}

	public Long getPackageUnitVolumeInMilliliters() {
		return packageUnitVolumeInMilliliters;
	}

	public void setPackageUnitVolumeInMilliliters(Long packageUnitVolumeInMilliliters) {
		this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
	}

	public Long getTotalPackageUnits() {
		return totalPackageUnits;
	}

	public void setTotalPackageUnits(Long totalPackageUnits) {
		this.totalPackageUnits = totalPackageUnits;
	}

	public Long getVolumeInMilliliters() {
		return volumeInMilliliters;
	}

	public void setVolumeInMilliliters(Long volumeInMilliliters) {
		this.volumeInMilliliters = volumeInMilliliters;
	}

	public Long getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(Long alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getReleasedOn() {
		return releasedOn;
	}

	public void setReleasedOn(String releasedOn) {
		this.releasedOn = releasedOn;
	}

	public Boolean getIsSeasonal() {
		return isSeasonal;
	}

	public void setIsSeasonal(Boolean isSeasonal) {
		this.isSeasonal = isSeasonal;
	}

	public Boolean getIsVqa() {
		return isVqa;
	}

	public void setIsVqa(Boolean isVqa) {
		this.isVqa = isVqa;
	}

	public Boolean getIsOcb() {
		return isOcb;
	}

	public void setIsOcb(Boolean isOcb) {
		this.isOcb = isOcb;
	}

	public Boolean getIsKosher() {
		return isKosher;
	}

	public void setIsKosher(Boolean isKosher) {
		this.isKosher = isKosher;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServingSuggestion() {
		return servingSuggestion;
	}

	public void setServingSuggestion(String servingSuggestion) {
		this.servingSuggestion = servingSuggestion;
	}

	public String getTastingNote() {
		return tastingNote;
	}

	public void setTastingNote(String tastingNote) {
		this.tastingNote = tastingNote;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImageThumbUrl() {
		return imageThumbUrl;
	}

	public void setImageThumbUrl(String imageThumbUrl) {
		this.imageThumbUrl = imageThumbUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVarietal() {
		return varietal;
	}

	public void setVarietal(String varietal) {
		this.varietal = varietal;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTertiaryCategory() {
		return tertiaryCategory;
	}

	public void setTertiaryCategory(String tertiaryCategory) {
		this.tertiaryCategory = tertiaryCategory;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", isDead=" + isDead + ", name=" + name + ", tags=" + tags + ", isDiscontinued="
				+ isDiscontinued + ", priceInCents=" + priceInCents + ", stockType=" + stockType + ", primaryCategory="
				+ primaryCategory + ", secondaryCategory=" + secondaryCategory + ", origin=" + origin
				+ ", packageUnitType=" + packageUnitType + ", packageUnitVolumeInMilliliters="
				+ packageUnitVolumeInMilliliters + ", totalPackageUnits=" + totalPackageUnits + ", volumeInMilliliters="
				+ volumeInMilliliters + ", alcoholContent=" + alcoholContent + ", producerName=" + producerName
				+ ", releasedOn=" + releasedOn + ", isSeasonal=" + isSeasonal + ", isVqa=" + isVqa + ", isOcb=" + isOcb
				+ ", isKosher=" + isKosher + ", description=" + description + ", servingSuggestion=" + servingSuggestion
				+ ", tastingNote=" + tastingNote + ", updatedAt=" + updatedAt + ", imageThumbUrl=" + imageThumbUrl
				+ ", imageUrl=" + imageUrl + ", varietal=" + varietal + ", style=" + style + ", tertiaryCategory="
				+ tertiaryCategory + "]";
	}

	


	
}
