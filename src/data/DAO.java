package data;
import java.io.*;
import java.util.ArrayList;

public class DAO<T> {
	 private String fileName;

	    public DAO() {
	    }

	    public DAO(String fileName) {
	        this.fileName = fileName;
	    }

	    public void saveToFile(ArrayList dataList) // save object to file
	    {
	        File file = new File(fileName);
	        try {
	            ObjectOutputStream ooStream
	                    = new ObjectOutputStream(new FileOutputStream(file));
	            ooStream.writeObject(dataList);
	            ooStream.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println("\nFile not found");
	        } catch (IOException ex) {
	            System.out.println(ex);
	            System.out.println("\nCannot save to file");
	        }
	    }

	    public ArrayList<T> retrieveFromFile() //get object in the file
	    {
	        File file = new File(fileName);
	        ArrayList<T> dataList = new ArrayList<>();
	        try {
	            ObjectInputStream oiStream
	                    = new ObjectInputStream(new FileInputStream(file));
	            dataList = (ArrayList<T>) (oiStream.readObject());
	            oiStream.close();
	        } catch (FileNotFoundException ex) {
	            System.out.println("\nNo such file.");
	        } catch (IOException ex) {
	            System.out.println(ex);

	            System.out.println("\nCannot read from file.");
	        } catch (ClassNotFoundException ex) {
	            System.out.println("\nClass not found.");
	        } finally {
	            return dataList;
	        }
	    }

	    public String getFileName() {
	        return fileName;
	    }

	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }
}
