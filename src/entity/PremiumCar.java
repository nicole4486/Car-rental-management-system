package entity;

public class PremiumCar extends Car{
	
	private int limitDay;
	
	public PremiumCar(int limitDay) {
		super();
		
		this.limitDay = limitDay;
	}

	public int getLimitDay() {
		return limitDay;
	}
	public void setLimitDay(int limitDay) {
		this.limitDay = limitDay;
	}
	public PremiumCar() {
		super();
		
	}
	public PremiumCar(String plateNum, String model, String colour, double rentPrice, int currentNumber, int limitDay) {
		super(plateNum, model, colour, rentPrice, currentNumber);
		this.limitDay = limitDay;
	}
	
	public String toString() {
        return super.toString() + String.format("Limited Day Rent : %-2d\n",limitDay);
    }
	
}
