package assignment;

import data.DAO;
import entity.Customer;
import entity.Staff;
import java.util.ArrayList;
import utility.CustomScanner;
import utility.GeneralUtility;

public class Main {

    CustomScanner cScan = new CustomScanner();
    GeneralUtility util = new GeneralUtility();

    //control class
    CustomerControl customerControl = new CustomerControl();
    RentControl rentControl = new RentControl();
    CarControl carControl = new CarControl();
    
    //data list
    ArrayList<Staff> staffList = new ArrayList<>();
    Staff currentStaff = new Staff();
    
    
    //data access
    DAO staffDao = new DAO("staff.txt");
    

    public Main()  //retrieve object from the file
    {
        staffList = staffDao.retrieveFromFile();      
   }

    public static void main(String[] args) {
        Main main = new Main();
        main.loginMenu();
    }

    public void loginMenu() {
        int choice;
        do {
            util.clearScreen();
            System.out.println("Car Management");
            System.out.println("--------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            choice = cScan.inputInt("Enter Selection > ", 0, 2);
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
            }

        } while (choice != 0);
    }

    public void login() {

        util.clearScreen();
        boolean isSuccessful = false;
        System.out.println("Login");
        System.out.println("----------------------------");
        String username = cScan.inputString("Enter Your Username > ");
        String password = cScan.inputString("Enter Your Password > ");

        for (int i = 0; i < staffList.size(); i++) {
            if (username.equals(staffList.get(i).getUsername())) {
                if (password.equals(staffList.get(i).getPassword())) {
                    isSuccessful = true;
                    currentStaff = staffList.get(i);
                }
            }
        }

        if (!isSuccessful) {
            System.out.println("Wrong Username or Password!");
            util.systemPause();
            return;
        }

        mainMenu();
    }
    
    public void register() {

        util.clearScreen();
        boolean isRegistered = false;
        
        System.out.println("Register");
        System.out.println("----------------------------");
        String username = cScan.inputString("Enter Your Username > ");
        String password = cScan.inputString("Enter Your Password > ");
        String name     = cScan.inputString("Enter Your Name     > ");

        for (int i = 0; i < staffList.size(); i++) {
            if (username.equals(staffList.get(i).getUsername()) && (password.equals(staffList.get(i).getPassword()))) 
        
                {
                    isRegistered = true;
                    System.out.println("Registered Username or Password!");
                    util.systemPause();
                    return;
                }
            }
        
        

        if (!isRegistered) {
            System.out.println("Registered Successfully!");
            Staff newStaff = new Staff(username,password,name);
            staffList.add(newStaff);
            staffDao.saveToFile(staffList);
            util.systemPause();
            return;
        }


    }

    public void mainMenu() {
        int choice;
        do {
            util.clearScreen();
            System.out.println("Hello, " + currentStaff.getName() + "\n");
            System.out.println(" Car Management Main Menu ");
            System.out.println("--------------------------");
            System.out.println("1. Customer Management    ");
            System.out.println("2. Rent Car");
            System.out.println("3. Car Owner Management");
            System.out.println("4. Rental History");
            System.out.println("5. Edit Your Details");
            System.out.println("0. Back");
            choice = cScan.inputInt("Enter Selection > ", 0, 5);
            switch (choice) {
                case 1:
                    customerControl.customerMainMenu();
                    break;
                case 2:
                	rentControl.rentCar();
                    break;
                case 3:
                	carControl.carMainMenu();
                    break;
                case 4:
                	rentControl.displayRentalHistory();               	
                    break;
                case 5:
                	editStaffDetails();
                    break;
            }
        } while (choice != 0);
    }
    
    public void editStaffDetails() //select details to edit
    {
    	int choice;
    	do {
    		util.clearScreen();
    		System.out.println("STAFF DETAILS MANAGEMENT");
	    	System.out.println("--------------------------------------");
	    	System.out.println("HI "+ currentStaff.getName());
	    	System.out.println("--------------------------------------");
	    	System.out.println("Your username is "+ currentStaff.getUsername());
	    	System.out.println("Your password is "+ currentStaff.getPassword());
	    	System.out.println("1. Edit name      ");
	        System.out.println("2. Edit username  ");
	        System.out.println("3. Edit password  ");
	        System.out.println("0. Exit > ");
	        
	        choice = cScan.inputInt("Enter Selection > ", 0, 3);
	        switch (choice) {
	        case 1:
	        	editName();	
	        	break;
	        case 2:
	        	editUsername();
	        	break;
	        case 3:
	        	editPassword();
	        }
    	}while(choice!=0);
    }
    
    public void editName()
    {
    	String newName = cScan.inputString("Enter new name > ");
        if (cScan.confimation("Are You Sure? (Y/N) > ")) {
            currentStaff.setName(newName);
            staffDao.saveToFile(staffList);
        }
        
	}
    
    public void editUsername()
    {
    	String newUsername = cScan.inputString("Enter new username > ");
        if (cScan.confimation("Are You Sure? (Y/N) > ")) 
        {
            currentStaff.setUsername(newUsername);
            staffDao.saveToFile(staffList);
        }
    }
    
    public void editPassword()
    {
    	String newPassword = cScan.inputString("Enter new password > ");
        if (cScan.confimation("Are You Sure? (Y/N) > ")) 
        {
            currentStaff.setPassword(newPassword);
            staffDao.saveToFile(staffList);
        }
    }
}
