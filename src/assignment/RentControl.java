package assignment;

import java.util.Date;
import java.text.SimpleDateFormat;

import data.DAO;
import entity.Car;
import entity.Customer;
import entity.PremiumCar;
import entity.RentalHistory;
import java.util.ArrayList;
import utility.CustomScanner;
import utility.GeneralUtility;

public class RentControl {

	CustomScanner cScan = new CustomScanner();
	GeneralUtility util = new GeneralUtility();

	// create a customer list
	static ArrayList<Customer> customerList = new ArrayList<>();

	// create a car list
	static ArrayList<Car> carList = new ArrayList();

	// create a history list
	static ArrayList<RentalHistory> historyList = new ArrayList();

	DAO customerDao = new DAO("customer.txt");
	DAO carDao = new DAO("car.txt");
	DAO RentHistoryDao = new DAO("history.txt");
	
	
	public RentControl() 
	{
		historyList = RentHistoryDao.retrieveFromFile();
	}

	public void rentCar() {
		
		util.clearScreen();
		customerList = customerDao.retrieveFromFile();
		
		carList = carDao.retrieveFromFile();
		int day;
		boolean rent;

		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rentDate = dateFormat.format(currentDate);

		System.out.println("Car Rental");
		System.out.println("========================================");

		findCustomer();
	}



	private void findCustomer() {// check whether the customer id exist and select the customer

		CustomerControl.displayAllCustomer();
		System.out.println("Edit Customer");
		System.out.println("---------------------");
		String selectCust = cScan.inputString("Enter Customer ID > ");
		selectCust = selectCust.toLowerCase();

		Customer foundCust = null;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getCustId().toLowerCase().equals(selectCust)) {
				foundCust = customerList.get(i);
			}
		}

		if (foundCust == null) {
			System.out.println("No Such Customer ID.");
			util.systemPause();
			return;
		}
		findCar(foundCust);
		

	}

	private void findCar(Customer foundCust) {// pass selected customer to here and check whether the car ID exist and select a carID
		int day;
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rentDate = dateFormat.format(currentDate);
		boolean found;
		
		Car foundCar = null;
		do {
			util.clearScreen();
			found = true;
			CarControl.displayAllCars();
			System.out.println("---------------------");
			String selectCar = cScan.inputString("Enter Car ID (Enter 0 to exit) > ");
			selectCar = selectCar.toLowerCase();

			

			for (int i = 0; i < carList.size(); i++) //loop the array list to find the same car ID, not found then key in again
			{
				if (carList.get(i).getCarID().toLowerCase().equals(selectCar)) {
					foundCar = carList.get(i);
					
				}
			}
			
			if (selectCar.equals("0"))
				return;		
			
			if (foundCar == null) {
				System.out.println("No such Car ID.");
				util.systemPause();
				found=false;
			}
		} while (!found);
		if(foundCar instanceof PremiumCar)// if it is premium car, limit the day for rent
		{
			PremiumCar premiumcar =(PremiumCar) foundCar;
			int limitDay = premiumcar.getLimitDay();
			day = cScan.inputInt("Enter number of days to rent (within " + limitDay + "days) > ",1,limitDay);
			
			if (day > limitDay) {
                System.out.println("Sorry, you can only rent this premium car for up to " + limitDay + " days.");
                util.systemPause();
                found = false;
			}
			
		}
		else {
		day = cScan.inputInt("Enter number of day to rent >");}
		
		dipslayRentInformation(foundCust, foundCar, day, rentDate);	
		
		RentalHistory history = new RentalHistory(foundCar,foundCust,rentDate,day);
		boolean rent = removeCar(foundCar);
		if (rent)
		{
			historyList.add(history);
			RentHistoryDao.saveToFile(historyList);
			System.out.println("Rental Confirmed !");
		}
		
			
		
		util.systemPause();

	}

	public void displayRentalHistory() //display all rental history
	{
		historyList = RentHistoryDao.retrieveFromFile();
		util.clearScreen();
		System.out.println("Rental Record  ");
		System.out.println("===================================================");
		System.out.printf("%-12s %-15s %-10s %-6s\n", "Date", "Customer ID", "Car ID", "Period(Day/s)");
		System.out.println("===================================================");

		if (historyList.isEmpty()) {
			System.out.println("No history Yet");
			util.systemPause();
			return;
		}
		for (int i = 0; i < historyList.size(); i++) {
			System.out.println(historyList.get(i));
			
		}
		util.systemPause();

	}

	private void dipslayRentInformation(Customer c, Car ca, int day, String date) //pass the selected car and customer and print the details
	{
		util.clearScreen();
		System.out.println("Rental Information       Date :" + date);
		System.out.println("===============================================");
		System.out.println("Customer ID             : " + c.getCustId());
		System.out.println("Customer Name           : " + c.getName());
		System.out.println("Customer Phone Number   : " + c.getPhoneNo());
		System.out.println("===============================================");
		System.out.println("Car ID                  : " + ca.getCarID());
		System.out.println("Plate Number            : " + ca.getPlateNum());
		System.out.println("Car Model               : " + ca.getModel());
		System.out.println("Car Colour              : " + ca.getColour());
		System.out.println("Rent Price              : " + ca.getRentPrice());
		System.out.println("===============================================");
		System.out.println("Rental Days             : " + day + " day/s");
		System.out.println("Total Rental Price      : " + ca.calculatePrice(day));
		System.out.println("---------------------------------------------- ");

	}

	private boolean removeCar(Car foundCar) {
		if (cScan.confimation("Are You Sure ? (Y/N) > ")) {
			carList.remove(foundCar);
			carDao.saveToFile(carList);
			Car.setTotalCar(Car.getTotalCar() - 1);
		return true;
	}		
		return false;
	}
	
	

}
