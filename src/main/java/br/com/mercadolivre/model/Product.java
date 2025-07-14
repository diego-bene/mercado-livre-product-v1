package br.com.mercadolivre.model;

import java.util.Date;
import java.util.List;

public class Product {
	
    private Long id;
	
    private String productTitle;
    
    private String productDescription;
    
    private String sellerInfo;
    
    private Double productRating; // Ex: média de estrelas
    
    private Double price;
    
    private Double oldPrice;
    
    private List<String> productFeatures;
    
    private List<String> productReviews;
    
    private List<String> imagePaths;
    
    private int availableStock;
    
    private ProductStatus status;
    
    private Date createDate;
    
    private Long createdUserId;
	
    public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getSellerInfo() {
		return sellerInfo;
	}
	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}
	public Double getProductRating() {
		return productRating;
	}
	public void setProductRating(Double productRating) {
		this.productRating = productRating;
	}
	public List<String> getProductReviews() {
		return productReviews;
	}
	public void setProductReviews(List<String> productReviews) {
		this.productReviews = productReviews;
	}
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getProductFeatures() {
		return productFeatures;
	}
	public void setProductFeatures(List<String> productFeatures) {
		this.productFeatures = productFeatures;
	}
	public List<String> getImagePaths() {
		return imagePaths;
	}
	public void setImagePaths(List<String> imagePaths) {
		this.imagePaths = imagePaths;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}
    
}
