package br.com.mercadolivre.model;

import java.util.Date;
import java.util.List;

public class ProductPurchase {
	
	private String id;
    
	private Long userId;
    
	private Date createDate;
    
	private Double totalAmount;
	
	private PaymentMethod paymentMethod;
    
	private List<ProductPurchaseItem> items;
    
    private PurchaseStatus status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<ProductPurchaseItem> getItems() {
		return items;
	}

	public void setItems(List<ProductPurchaseItem> items) {
		this.items = items;
	}

	public PurchaseStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseStatus status) {
		this.status = status;
	}

}
