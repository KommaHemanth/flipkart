package com.insignia.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_sequence_number")
	private Long productSequenceNumber;

	@Column(name = "product_id", unique = true)
	private String productId;

	@Column(name = "product_name", unique = true)
	private String productName;

	@Column(name = "description")
	private String description;

	@Column(name = "measuring_quantity")
	private String measuringQuantity;

	@Column(name = "measuring_unit")
	private String measuringUnit;

	@Column(name = "subcategory_id")
	private Long subcategoryId;

	@Column(name = "product_image")
	private String productImage;

	@Column(name = "product_per_unit_actual_price", length = 10)
	private Float productPerUnitActualPrice;

	@Column(name = "product_per_unit_current_price", length = 10)
	private Float productPerUnitCurrentPrice;

	@Column(name = "length", length = 7)
	private Float length;

	@Column(name = "width", length = 7)
	private Float width;

	@Column(name = "height", length = 7)
	private Float height;

	@Column(name = "dimension_unit", length = 10)
	private String dimensionUnit;

	@Column(name = "materials", length = 128)
	private String materials;

	@Column(name = "colours", length = 128)
	private String colours;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_sequence_number")
	private List<CustomerCartInformation> customerCartInformations;

	@OneToMany(mappedBy = "productDetails")
	private List<OrderAndProductLink> orderAndProductLinkList;

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
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeasuringQuantity() {
		return measuringQuantity;
	}

	public void setMeasuringQuantity(String measuringQuantity) {
		this.measuringQuantity = measuringQuantity;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}

	public void setMeasuringUnit(String measuringUnit) {
		this.measuringUnit = measuringUnit;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Float getProductPerUnitActualPrice() {
		return productPerUnitActualPrice;
	}

	public void setProductPerUnitActualPrice(Float productPerUnitActualPrice) {
		this.productPerUnitActualPrice = productPerUnitActualPrice;
	}

	public Float getProductPerUnitCurrentPrice() {
		return productPerUnitCurrentPrice;
	}

	public void setProductPerUnitCurrentPrice(Float productPerUnitCurrentPrice) {
		this.productPerUnitCurrentPrice = productPerUnitCurrentPrice;
	}

	public Float getLength() {
		return length;
	}

	public void setLength(Float length) {
		this.length = length;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public String getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(String dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getColours() {
		return colours;
	}

	public void setColours(String colours) {
		this.colours = colours;
	}

	public List<CustomerCartInformation> getCustomerCartInformations() {
		return customerCartInformations;
	}

	public void setCustomerCartInformations(List<CustomerCartInformation> customerCartInformations) {
		this.customerCartInformations = customerCartInformations;
	}

	public List<OrderAndProductLink> getOrderAndProductLinkList() {
		return orderAndProductLinkList;
	}

	public void setOrderAndProductLinkList(List<OrderAndProductLink> orderAndProductLinkList) {
		this.orderAndProductLinkList = orderAndProductLinkList;
	}

	public ProductDetails(Long productSequenceNumber, String productId, String productName, String description,
			String measuringQuantity, String measuringUnit, Long subcategoryId, String productImage,
			Float productPerUnitActualPrice, Float productPerUnitCurrentPrice, Float length, Float width, Float height,
			String dimensionUnit, String materials, String colours) {
		super();
		this.productSequenceNumber = productSequenceNumber;
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.measuringQuantity = measuringQuantity;
		this.measuringUnit = measuringUnit;
		this.subcategoryId = subcategoryId;
		this.productImage = productImage;
		this.productPerUnitActualPrice = productPerUnitActualPrice;
		this.productPerUnitCurrentPrice = productPerUnitCurrentPrice;
		this.length = length;
		this.width = width;
		this.height = height;
		this.dimensionUnit = dimensionUnit;
		this.materials = materials;
		this.colours = colours;
	}

	public ProductDetails() {
		super();
	}

}
