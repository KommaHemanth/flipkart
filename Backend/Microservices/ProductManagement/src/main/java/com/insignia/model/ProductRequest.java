package com.insignia.model;

public class ProductRequest {

	private Long productSequenceNumber;
	private String productId;
	private boolean isProductIdUpdated;

	// updated remaining only
	private String productName;
	private boolean isProductNameUpdated;

	private String description;
	private boolean isDescriptionUpdated;

	private String measuringQuantity;
	private boolean isMeasuringQuantityUpdated;

	private String measuringUnit;
	private boolean isMeasuringUnitUpdated;

	private Long subcategoryId;
	private boolean isSubcategoryIdUpdated;

	private String productImage;
	private boolean isProductImageUpdated;

	private Float productPerUnitActualPrice;
	private boolean isProductPerUnitActualPriceUpdated;

	private Float productPerUnitCurrentPrice;
	private boolean isProductPerUnitCurrentPriceUpdated;

	private Float productLength;
	private boolean isLengthUpdated;

	private Float width;
	private boolean isWidthUpdated;

	private Float height;
	private boolean isHeightUpdated;

	private String dimensionUnit;
	private boolean isDimensionUnitUpdated;

	private String materials;
	private boolean isMaterialsUpdated;

	private String colours;
	private boolean isColoursUpdated;

	
	public Long getProductSequenceNumber() {
		return productSequenceNumber;
	}

	public void setProductSequenceNumber(Long productSequenceNumber) {
		this.productSequenceNumber = productSequenceNumber;
	}


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
		this.isProductIdUpdated = true;
	}

	public boolean isProductIdUpdated() {
		return isProductIdUpdated;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
		this.isProductNameUpdated = true;
	}

	public boolean isProductNameUpdated() {
		return isProductNameUpdated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.isDescriptionUpdated = true;
	}

	public boolean isDescriptionUpdated() {
		return isDescriptionUpdated;
	}

	public String getMeasuringQuantity() {
		return measuringQuantity;
	}

	public void setMeasuringQuantity(String measuringQuantity) {
		this.measuringQuantity = measuringQuantity;
		this.isMeasuringQuantityUpdated = true;
	}

	public boolean isMeasuringQuantityUpdated() {
		return isMeasuringQuantityUpdated;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
		this.isMeasuringUnitUpdated = true;
	}

	public boolean isMeasuringUnitUpdated() {
		return isMeasuringUnitUpdated;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
		this.isSubcategoryIdUpdated = true;
	}

	public boolean isSubcategoryIdUpdated() {
		return isSubcategoryIdUpdated;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
		this.isProductImageUpdated = true;
	}

	public boolean isProductImageUpdated() {
		return isProductImageUpdated;
	}

	public Float getProductPerUnitActualPrice() {
		return productPerUnitActualPrice;
	}

	public void setProductPerUnitActualPrice(Float productPerUnitActualPrice) {
		this.productPerUnitActualPrice = productPerUnitActualPrice;
		this.isProductPerUnitActualPriceUpdated = true;
	}

	public boolean isProductPerUnitActualPriceUpdated() {
		return isProductPerUnitActualPriceUpdated;
	}

	public Float getProductPerUnitCurrentPrice() {
		return productPerUnitCurrentPrice;
	}

	public void setProductPerUnitCurrentPrice(Float productPerUnitCurrentPrice) {
		this.productPerUnitCurrentPrice = productPerUnitCurrentPrice;
		this.isProductPerUnitCurrentPriceUpdated = true;
	}

	public boolean isProductPerUnitCurrentPriceUpdated() {
		return isProductPerUnitCurrentPriceUpdated;
	}

	public Float getProductLength() {
		return productLength;
	}

	public void setProductLength(Float productLength) {
		this.productLength = productLength;
		this.isLengthUpdated = true;
	}

	public boolean isLengthUpdated() {
		return isLengthUpdated;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
		this.isWidthUpdated = true;
	}

	public boolean isWidthUpdated() {
		return isWidthUpdated;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
		this.isHeightUpdated = true;
	}

	public boolean isHeightUpdated() {
		return isHeightUpdated;
	}

	public String getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(String dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
		this.isDimensionUnitUpdated = true;
	}

	public boolean isDimensionUnitUpdated() {
		return isDimensionUnitUpdated;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
		this.isMaterialsUpdated = true;
	}

	public boolean isMaterialsUpdated() {
		return isMaterialsUpdated;
	}

	public String getColours() {
		return colours;
	}

	public void setColours(String colours) {
		this.colours = colours;
		this.isColoursUpdated = true;
	}

	public boolean isColoursUpdated() {
		return isColoursUpdated;
	}

}
