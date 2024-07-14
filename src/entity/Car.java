package entity;

import java.io.Serializable;


public class Car implements Serializable{
	private String carID;  
	
	private String plateNum;
	
	private String model;
	
	private String colour;
	
	private double rentPrice;
	
	static int totalCar = 0;
	
	//private boolean isAvailable;
	
	public String getCarID() {
		return carID;
	}
	
	//get&set
	public String getPlateNum() {
		return plateNum;
	}
	
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model=model;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		this.colour=colour;
	}
	
	public double getRentPrice() {
		return rentPrice;
	}
	
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	
	public static void setTotalCar(int totalCar) {
        Car.totalCar = totalCar;
    }
	
	public static int getTotalCar() {
		return totalCar;
	}
	
	//constructor 
	public Car() {
		
	}
	public Car(String plateNum,String model,String colour,double rentPrice,int currentNumber)
	{	
		this.carID = String.format("R%04d",currentNumber);
		this.plateNum = plateNum;
		this.model = model;
		this.colour = colour;
		this.rentPrice = rentPrice;
		totalCar++;
		
	}
	
	//method
	public double calculatePrice(int day) {
		return rentPrice * day;
	}
	
	 public String toString() {
	        return String.format("%-10s %-10s %-10s %-8s %-6s\n", carID, plateNum, model,colour,rentPrice);
	    }
	
	
	/*public void toRent() {
		isAvailable = false;
	}*/
	 
}

