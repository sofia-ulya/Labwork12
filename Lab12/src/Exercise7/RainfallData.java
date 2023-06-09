package Exercise7;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RainfallData {

	private static String rainfallFile;
	private static double totalRainfallRate;
	private static int noOfDays;
	
	// Method to write rainfall data
	public static void writeRainfallData(String[] rainfallDate, int[] rainfallRate) {
		try {

			// Create a writer
			PrintWriter fileWriter = new PrintWriter(rainfallFile);

			// Write data
			for (int index = 0; index < rainfallDate.length; index++) {
				fileWriter.write(rainfallDate[index]);
				fileWriter.write(rainfallRate[index]);
			}
			
			// Clear the writer
			fileWriter.flush();

			// Close the stream
			fileWriter.close();  

		} catch (IOException e) {
			
			e.printStackTrace();
		}  

		// Indicate end of program - Could be successful
		System.out.println("Check out " + rainfallFile+ " file. \n\nEnd of program."); 
		System.out.println("----------------------------------------------------");
	}
	
	
	// Method to read rainfall data
	public static void readRainfallData() {
		
		System.out.println("\nThe following are the rainfall rate at Simpang Ampat, Melaka:\n");
		try {
			
			// Create object
			FileReader fileReader = new FileReader(rainfallFile);
			
			// Read values from the reader
			int i = 0;noOfDays=0;
			char[] date = new char[8];
			// Read date
			i=fileReader.read(date);
			while (i != -1) {
				System.out.print(date);
				
				//Read rate
				i=fileReader.read();
				System.out.print("\t"+i+"\n");
				totalRainfallRate+=i;
				noOfDays++;
				// Read next value
				i=fileReader.read(date);
			} 
			
			// Close stream
			fileReader.close();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		// Indicate end of program - Could be successful
		System.out.println("\nEnd of program.");
		System.out.println("----------------------------------------------------"); 
	}
	
	// Main function
	public static void main(String[] args) {
		// Declare file
		rainfallFile = "RainfallData_SimpangAmpat.txt2";
		
		// Declare variable
		String[] rainfallDate = {"3/6/2023","4/6/2023","5/6/2023","6/6/2023","7/6/2023","8/6/2023"};
		int rainfallRate[] = {4,1,0,6,19,1};
		
		
		// Call method to write data
		writeRainfallData(rainfallDate, rainfallRate);
		
		// Call method to read data
		readRainfallData();
		
		// Calculate average rainfall rate
		String averageRainfallRate = String.format("%.1f", totalRainfallRate / noOfDays);
		System.out.print("\nAverage rainfall at Simpang Ampat for " + noOfDays + " days: " + averageRainfallRate + "%");
	}
}
