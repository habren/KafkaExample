package com.jasongj.kafka.stream.model;

public class User {
	private String name;
	private String address;
	private String gender;
	private int age;

	public User() {}
	
	public User(String name, String address, String gender, int age) {
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
