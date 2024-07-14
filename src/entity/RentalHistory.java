package entity;

import java.io.Serializable;
import java.util.Date;

public class RentalHistory implements Serializable {

	private Car property;
	
	private Customer lessee;
	
	private String rentDate;
	
	private int period;
	
	//get&set 
	public Car getProperty() {
		return property;
	}
	
	public Customer getLessee() {
		return lessee;
	}
	
	public String getRentDate()
	{
		return rentDate;
	}
	
	public int getPriod() {
		return period;
	}
	
	//constructor 
	public RentalHistory(Car property,Customer lessee,String rentDate,int period) {
		this.property = property;
		this.lessee = lessee;
		this.rentDate = rentDate;
		this.period = period;
	}
	
	 public String toString() {
	        return String.format("%-12s %-15s %-10s %-6s\n",rentDate,lessee.getCustId(),property.getCarID(),period);
	    }
}
