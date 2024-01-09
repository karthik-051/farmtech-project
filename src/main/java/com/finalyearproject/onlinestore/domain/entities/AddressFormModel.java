package com.mitron.onlinestore.domain.entities;

public class AddressFormModel {
    private String addressLine;
    private String city;
    private String postalCode;
    // Other address-related fields, getters, and setters
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
    
}
