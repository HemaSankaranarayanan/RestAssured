package Hema.restassured;

import java.util.List;

public class Apartment {

	private String id;
	private String address;
	private double price;
	private int square;
	private List<String> features;
	private boolean active;
	
	public Apartment() {
		
	}
	
	public Apartment(String address, double price, int square) {
		this.address = address;
		this.price = price;
		this.square = square;
	}
	
	public Apartment(String address, double price, int square, List<String> features, boolean active) {
		this.address = address;
		this.price = price;
		this.square = square;
		this.features = features;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSquare() {
		return square;
	}

	public void setSquare(int square) {
		this.square = square;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
}
