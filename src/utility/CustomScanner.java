package utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomScanner {
	 Scanner scan = new Scanner(System.in);

	    public double inputDouble(String str, double min, double max) { //with constraint
	        boolean error;
	        double output = -999;

	        do {
	            error = false;
	            output = this.inputDouble(str);
	            if (output < min || output > max) {
	                error = true;
	                System.err.printf("Please enter number between [ %.2f ~ %.2f ]\n", min, max);
	            }
	        } while (error);

	        return output;
	    }

	    public double inputDouble(String str) //override function to check validation
	    {
	        double output = -999;
	        boolean error;

	        do {
	            error = false;
	            System.out.print(str);
	            try {
	                output = scan.nextDouble();
	            } catch (InputMismatchException ex) {
	                System.err.println("Please enter number only.");
	                scan.next(); //clear buffer
	                error = true;
	            }
	        } while (error);

	        this.clearBuffer();
	        return output;
	    }

	    public void clearBuffer() {
	        scan.nextLine();
	    }

	    public String inputString(String str) {
	        System.out.print(str);
	        return scan.nextLine();
	    }

	    public char inputChar(String str) {
	        char ch = 'X';
	        boolean error;

	        do {
	            System.out.print(str);
	            error = false;
	            try {
	                ch = scan.nextLine().charAt(0);
	            } catch (Exception e) {
	                System.err.println("Please enter your input.");
	                error = true;
	            }
	        } while (error);

	        return ch;
	    }

	    public char inputChar(String str, String errorMsg, char[] checkChar) {
	        char output;
	        boolean error = true;

	        do {
	            output = this.inputChar(str);
	            for (char ch : checkChar) {
	                if (Character.toUpperCase(output) == ch) {
	                    error = false;
	                    break;
	                }
	            }

	            if (error) {
	                System.err.println(errorMsg);
	            }
	        } while (error);

	        return output;
	    }

	    public int inputInt(String str) {
	        int output = -999;
	        boolean error;

	        do {
	            error = false;
	            System.out.print(str);
	            try {
	                output = scan.nextInt();
	            } catch (InputMismatchException ex) {
	                System.err.println("Please enter number only.");
	                scan.next(); //clear buffer
	                error = true;
	            }
	        } while (error);

	        this.clearBuffer();
	        return output;
	    }

	    public int inputInt(String str, int min, int max) { //with constraint
	        boolean error;
	        int output = -999;

	        do {
	            error = false;
	            output = this.inputInt(str);
	            if (output < min || output > max) {
	                error = true;
	                System.err.println("Please enter number between [" + min
	                        + "~" + max + "].");
	            }
	        } while (error);

	        return output;
	    }

	    public boolean confimation(String str) {
	        char[] checkCh = {'Y', 'N'};
	        char ch = inputChar(str,
	                "Please enter [Y] or [N].", checkCh);
	        if (Character.toUpperCase(ch) == 'Y') {
	            return true;
	        }
	        return false;
	    }
}
