package assignment;

import data.DAO;
import entity.Car;
import entity.Customer;
import entity.PremiumCar;
import java.util.ArrayList;
import utility.CustomScanner;
import utility.GeneralUtility;

public class CarControl {

	CustomScanner cScan = new CustomScanner();
	GeneralUtility util = new GeneralUtility();

	
	static ArrayList<Car> carList = new ArrayList<>();
	int lastCarId;

	DAO carDao = new DAO("car.txt");

	public CarControl() {
		//put in data into text file first, otherwise cannot run
		carList = carDao.retrieveFromFile();
		//retrieve object from the text file and put into array list
		lastCarId = Integer.parseInt(carList.get(carList.size() - 1).getCarID().substring(1));
		//get the last car id so that wont repeat car id
		Car.setTotalCar(carList.size());

	}
	public void carMainMenu()
	{
		carList = carDao.retrieveFromFile();
		int choice;
		do
		{
			
			util.clearScreen();
			System.out.println("    Car Management   ");
			System.out.println("--------------------------\n");
			displayAllCars();
			System.out.println("\n1. Add Car");
			System.out.println("2. Add Premium Car");
			System.out.println("3. Edit / Remove Car");
			System.out.println("0. Back");
			choice = cScan.inputInt("Enter Selection > ", 0, 3);
			
			switch(choice)
			{
				case 1:
					addCar();
					break;
				case 2:
					addPremiumCar();
					break;
				case 3:
					util.clearScreen();
					editCar();
					break;
			}
			
		}while(choice !=0);
	}
	public static void displayAllCars() // display all car
	{
		System.out.println("===============================================");
		System.out.println("Car List");
		System.out.println("===============================================");
		System.out.printf("%-10s %-10s %-10s %-8s %-6s\n", "ID", "PlateNum", "Model", "Colour", "Price");
		System.out.println("===============================================");
		
		if(carList.isEmpty()) 
		{
			System.out.println("No Cars Yet");
			return;
		}
		
		for (int i = 0; i < carList.size(); i++)
		{
			System.out.println(carList.get(i));
		}
		System.out.println("\nTotal Cars: " + Car.getTotalCar());
	}


	private void addCar() // add car, add to array list, save to file
	{	
		do
		{
		
			util.clearScreen();
			System.out.println("Add New Car");
			System.out.println("----------------");
			String plateNum = cScan.inputString("Enter Plate Number > ");
			String model = cScan.inputString("Enter Car Model > ");
			String colour = cScan.inputString("Enter Car Colour > ");
			double rentPrice = cScan.inputDouble("Enter Rent Price > ");
			
			if (cScan.confimation("Are You Sure To Add This Car? (Y/N) > "))
			{
				lastCarId++;
				Car newCar = new Car(plateNum, model, colour, rentPrice,lastCarId);
				carList.add(newCar); 
				carDao.saveToFile(carList); 
				System.out.println("New Car Added Successfully.");
			}
			
		}while (cScan.confimation("Add Another Car? (Y/N) > "));
		
	}
	
	private void addPremiumCar()	// add premium car, add to array list, save to file
	{
		do {
	        util.clearScreen();
	        System.out.println("Add New Premium Car");
	        System.out.println("----------------");
	        String plateNum = cScan.inputString("Enter Plate Number > ");
	        String model = cScan.inputString("Enter Car Model > ");
	        String colour = cScan.inputString("Enter Car Colour > ");
	        double rentPrice = cScan.inputDouble("Enter Rent Price > ");
	        int limitDay = cScan.inputInt("Enter Limited Day Rent > ");

	        if (cScan.confimation("Are You Sure To Add This Premium Car? (Y/N) > ")) {
	            lastCarId++;
	            PremiumCar newPremiumCar = new PremiumCar(plateNum, model, colour, rentPrice, lastCarId, limitDay);
	            carList.add(newPremiumCar);
	            carDao.saveToFile(carList);
	            System.out.println("New Premium Car Added Successfully.");
	        }
	    } while (cScan.confimation("Add Another Premium Car? (Y/N) > "));
	}


	private void displayCarDetails(Car foundCar) //display selected Car Details
	{
		System.out.println("\nCar Details");
		System.out.println("-------------------");
		System.out.println("ID               : " + foundCar.getCarID());
		System.out.println("Plate Number     : " + foundCar.getPlateNum());
		System.out.println("Model            : " + foundCar.getModel());
		System.out.println("Colour           : " + foundCar.getColour());
		System.out.println("Rent Price       : " + foundCar.getRentPrice());
		System.out.println("-------------------");
		 
	}
	private void editCar() //select car to edit
	{
		util.clearScreen();
		displayAllCars();
		System.out.println("Edit Car");
	    System.out.println("---------------------");
	    String selectCar = cScan.inputString("Enter Car ID > ");
		selectCar= selectCar.toLowerCase();
		
		Car foundCar=null;
		
		for (int i = 0; i < carList.size(); i++) //loop the array list to match the car ID, if not found, return to option add,edit car
		{
	        if (carList.get(i).getCarID().toLowerCase().equals(selectCar)) {
	            foundCar = carList.get(i);
	        }
	    }

	    if (foundCar == null) {
	        System.out.println("No Such Customer ID.");
	        util.systemPause();
	        return;
	    }
	    editCarDetails(foundCar);
		
	}
	private void editCarDetails(Car foundCar) // edit car details
	{
		int choice;
		boolean isRemove=false;
		do
		{
			util.clearScreen();
			displayCarDetails(foundCar);
			System.out.println("1. Edit Plate Number");
			System.out.println("2. Edit Model");
			System.out.println("3. Edit Colour");
			System.out.println("4. Edit Rent Price");
			System.out.println("5. REMOVE Car");
			System.out.println("0. Back");
			choice = cScan.inputInt("Enter Selection > ", 0, 5);
			
			switch(choice)
			{
				case 1:
					editCarPlateNum(foundCar);
					break;
				case 2:
					editCarModel(foundCar);
					break;
				case 3:
					editCarColour(foundCar);
					break;
				case 4:
					editCarRentPrice(foundCar);
					break;
				case 5:
					isRemove = removeCar(foundCar);
					break;
		
			}
			
			if(isRemove)
			{
				return;
			}
		}while(choice !=0);
	}


	private void editCarPlateNum(Car foundCar)
	{
		String newPlateNum = cScan.inputString("Enter New Plate Number > ");
		if (cScan.confimation("Are You Sure? (Y/N) > "))
		{
			foundCar.setPlateNum(newPlateNum);
			carDao.saveToFile(carList);
		}
		
	}

	private void editCarModel(Car foundCar)
	{
		String newModel = cScan.inputString("Enter New Model > ");
		if (cScan.confimation("Are You Sure? (Y/N) > "))
		{
			foundCar.setModel(newModel);
			carDao.saveToFile(carList);
			
		}
	}

	private void editCarColour(Car foundCar)
	{
		String newColour = cScan.inputString("Enter New Colour > ");
		if (cScan.confimation("Are You Sure? (Y/N) > "))
		{
			foundCar.setColour(newColour);
			carDao.saveToFile(carList);
		}
		
	}

	private void editCarRentPrice(Car foundCar)
	{
		double newRentPrice = cScan.inputDouble("Enter New Rent Price > ");
		if (cScan.confimation("Are You Sure? (Y/N) > "))
		{
			foundCar.setRentPrice(newRentPrice);
			carDao.saveToFile(carList);
		}
		
	}

	private boolean removeCar(Car foundCar)
	{
		if (cScan.confimation("Are You Sure? (Y/N) > "))
		{
			carList.remove(foundCar);
			carDao.saveToFile(carList);
			Car.setTotalCar(Car.getTotalCar()-1);
			return true;    //return true if the car was removed
		}
			return false;   //return false if no confirm the removal
		
	}
}