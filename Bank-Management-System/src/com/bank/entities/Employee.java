package com.bank.entities;

public class Employee 
{
	private int id;
	private String name;
	private long phone;
	private String email;
	private  String city;
	
	
	
	
	public Employee() {
		super();
	}

	
	public Employee(int id, String name, long phone, String email, String city) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", city=" + city
				+ "]";
	}
	
	
	
}
