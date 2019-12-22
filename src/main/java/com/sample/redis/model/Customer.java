package com.sample.redis.model;

import java.time.LocalDate;

public class Customer {
	
	private String customerId;
	private String biller;
	private String reference1;
	private LocalDate billDueDate;
	
	public Customer(String customerId, String biller, String reference1, LocalDate billDueDate) {
		this.customerId = customerId;
		this.biller = biller;
		this.reference1 = reference1;
		this.billDueDate = billDueDate;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBiller() {
		return biller;
	}
	public void setBiller(String biller) {
		this.biller = biller;
	}
	public String getReference1() {
		return reference1;
	}
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}
	
	public LocalDate getBillDueDate() {
		return billDueDate;
	}
	public void setbillDueDate(LocalDate billDueDate) {
		this.billDueDate = billDueDate;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", biller=" + biller + ", reference1=" + reference1
				+ ", billDueDate=" + billDueDate + "]";
	}

}
