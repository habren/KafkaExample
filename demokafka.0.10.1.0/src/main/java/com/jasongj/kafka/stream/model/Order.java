package com.jasongj.kafka.stream.model;

public class Order {

	private String userName;
	private String itemName;
	private long transactionDate;
	private int quantity;

	public Order() {}
	
	public Order(String userName, String itemName, long transactionDate, int quantity) {
		this.userName = userName;
		this.itemName = itemName;
		this.transactionDate = transactionDate;
		this.quantity = quantity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(long transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
