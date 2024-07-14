package utility;
import java.util.Scanner;

public class GeneralUtility {
	 public static void clearScreen() //clear the screen with a lot space
	 {
		 System.out.print("\n\n\n\n\n\n\n\n\n\n");
		 
	    }

	    public static void systemPause() //pause and continue execution
	    {
	        System.out.println("Press Enter To Continue...");
	        new Scanner(System.in).nextLine();
	    }
}
