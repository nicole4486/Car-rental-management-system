package entity;

import java.io.Serializable;

public class Customer implements Serializable{
	private String custId;
    private String name;
    private String phoneNo;
    static int totalCustomer = 0;

    public Customer() {
    }

    public Customer(String name, String phoneNo, int currentNumber) {
        this.custId = String.format("C%04d", currentNumber);
        this.name = name;
        this.phoneNo = phoneNo;
        totalCustomer++;
    }

    public String getCustId() {
        return custId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public static int getTotalCustomer() {
        return totalCustomer;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public static void setTotalCustomer(int totalCustomer) {
        Customer.totalCustomer = totalCustomer;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-20s %-20s", custId, name, phoneNo);
    }
}
