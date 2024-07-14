package assignment;


import data.DAO;
import entity.Customer;
import java.util.ArrayList;
import utility.CustomScanner;
import utility.GeneralUtility;

public class CustomerControl {

    CustomScanner cScan = new CustomScanner();
    GeneralUtility util = new GeneralUtility();

    static ArrayList<Customer> customerList = new ArrayList<>();
    int lastCustomerId;
    
    DAO customerDao = new DAO("customer.txt");
    

    public CustomerControl() {

        customerList = customerDao.retrieveFromFile();
        //retrieve object from text file, and put into array list
        lastCustomerId = Integer.parseInt(customerList
                .get(customerList.size() - 1)
                .getCustId()
                .substring(1));
      //get the last customer id so that wont repeat customer id
        Customer.setTotalCustomer(customerList.size());
        
    }

    public void customerMainMenu() {
    	
    	int choice;
        do {
        	
            util.clearScreen();
            System.out.println("    Customer Management   ");
            System.out.println("--------------------------\n");
            displayAllCustomer();
            System.out.println("\n1. Add Customer");
            System.out.println("2. Edit / Remove Customer");
            System.out.println("0. Back");
            choice = cScan.inputInt("Enter Selection > ", 0, 3);
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    editCustomer();
                    break;

            }
        } while (choice != 0);
    }

    public static void displayAllCustomer() //display all customer
    {
        System.out.println("Customer List  ");
        System.out.println("========================================");
        System.out.printf("%-6s %-20s %-20s\n", "ID", "Name", "Phone Number");
        System.out.println("========================================");

        if (customerList.isEmpty()) {
            System.out.println("No Customer Yet");
            return;
        }

        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(customerList.get(i));
        }
        System.out.println("Total Customer: "+Customer.getTotalCustomer());
    }

    private void addCustomer() //add customer, save it to array list and save it to file 
    {

    	do {
            util.clearScreen();
            System.out.println("Add New Customer");
            System.out.println("----------------");
            String name = cScan.inputString("Enter Customer Name > ");
            String phoneNo = cScan.inputString("Enter Phone Number > ");

            if (cScan.confimation("Are You Sure To Add This Customer? (Y/N) > ")) {
                lastCustomerId++;
                Customer newCustomer = new Customer(name, phoneNo, lastCustomerId);
                customerList.add(newCustomer);
                customerDao.saveToFile(customerList);
                System.out.println("New Customer Added Successfully.");
            }
        } while (cScan.confimation("Anymore More To Add? (Y/N) > "));
            

    }

    private void editCustomer() //select customer to be edited
    {
        util.clearScreen();
        displayAllCustomer();
        System.out.println("Edit Customer");
        System.out.println("---------------------");
        String selectCust = cScan.inputString("Enter Customer ID > ");
        selectCust = selectCust.toLowerCase();

        Customer foundCust = null;

        for (int i = 0; i < customerList.size(); i++) //loop the array list to match the customer ID, if not found, return to option add,edit customer
        {
            if (customerList.get(i).getCustId().toLowerCase().equals(selectCust)) {
                foundCust = customerList.get(i);
            }
        }

        if (foundCust == null) {
            System.out.println("No Such Customer ID.");
            util.systemPause();
            return;
        }

        editCustomerDetails(foundCust);
    }

    private void displayCustomerDetails(Customer foundCust) //display selected customer details
    {
        System.out.println("\nCustomer Details");
        System.out.println("-------------------");
        System.out.println("ID               : " + foundCust.getCustId());
        System.out.println("Name             : " + foundCust.getName());
        System.out.println("Phone Number     : " + foundCust.getPhoneNo());
        System.out.println("-------------------");

    }

    private void editCustomerDetails(Customer foundCust) //edit customer details
    {
        int choice;
        boolean isRemove = false;
        do {
            util.clearScreen();
            displayCustomerDetails(foundCust);
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Phone Number");
            System.out.println("3. REMOVE Customer");
            System.out.println("0. Back");
            choice = cScan.inputInt("Enter Selection > ", 0, 3);
            switch (choice) {
                case 1:
                    editCustomerName(foundCust);
                    break;
                case 2:
                    editCustomerPhoneNo(foundCust);
                    break;
                case 3:
                    isRemove = removeCustomer(foundCust);
                    break;
            }

            if (isRemove) {
                return;
            }

        } while (choice != 0);
    }

    private void editCustomerPhoneNo(Customer foundCust) {
    	String newPhoneNo;
    	boolean checking;
    	
    	do {
        newPhoneNo = cScan.inputString("Enter New Phone Number > ");
        checking=true;
        
        try
        {
     
        int newPhoneNum=Integer.parseInt(newPhoneNo);
        }
        catch (NumberFormatException e) 
        {
        	System.out.println("Please enter number!");
        	checking=false;
        }}
        while(!checking);

        if (cScan.confimation("Are You Sure? (Y/N) > ")) {
            foundCust.setPhoneNo(newPhoneNo);
            customerDao.saveToFile(customerList);
        }
    }

    private void editCustomerName(Customer foundCust) {
        String newName = cScan.inputString("Enter New Name > ");
        if (cScan.confimation("Are You Sure? (Y/N) > ")) {
            foundCust.setName(newName);
            customerDao.saveToFile(customerList);
        }
    }

    private boolean removeCustomer(Customer foundCust) {
        if (cScan.confimation("Are You Sure? (Y/N) > ")) {
            customerList.remove(foundCust);
            customerDao.saveToFile(customerList);
            Customer.setTotalCustomer(Customer.getTotalCustomer()-1);
            return true;
        }

        return false;
    }
}