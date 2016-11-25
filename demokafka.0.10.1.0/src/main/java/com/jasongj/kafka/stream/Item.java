package com.jasongj.kafka.stream;

public class Item {
	private String itemName;
	private String address;
	private String type;
	private double price;

	public Item() {}
	
	public Item(String itemName, String address, String type, double price) {
		this.itemName = itemName;
		this.address = address;
		this.type = type;
		this.price = price;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
