package Exercise5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RainfallData {

	private static String rainfallFile;
	private static double totalRainfallRate;
	private static int noOfDays;
	
	// Method to generate rainfall data
	public static void generateRainfallData(String[] rainfallDate, int[] rainfallRate) {
		try {
			
			// Create stream to read data
			DataOutputStream dataStream = new DataOutputStream(new FileOutputStream(rainfallFile));
			
			// Process data
			for (int index = 0; index < rainfallDate.length; index++) {
				
				// Write data into data stream
				dataStream.writeUTF(rainfallDate[index]);
				dataStream.writeInt(rainfallRate[index]);
				
				// Flush for each writing
				dataStream.flush();
			}
			
			// Close stream
			dataStream.close();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		// Indicate end of program - Could be successful
		System.out.println("Check out " + rainfallFile+ " file. \n\nEnd of program."); 
		System.out.println("----------------------------------------------------");
	}
	
	// Method to read rainfall data
	public static void readRainfallData() {
		System.out.println("\nThe following are the rainfall rate at Simpang Ampat, Melaka:\n");
		try {

			// Create stream to read data
			DataInputStream dataStream = new DataInputStream(new FileInputStream(rainfallFile));

			String rainfallDate = "";
			int rainfallRate = 0;
			
			// Process data until end-of-file
			while(dataStream.available() > 0) {
				
				// Read data
				rainfallDate = dataStream.readUTF();
				rainfallRate = dataStream.readInt();
				System.out.println( rainfallDate + "\t" + rainfallRate);
				
				// Calculate total rainfall rate
				totalRainfallRate += rainfallRate;
				noOfDays++;
			}
			
			// Close stream
			dataStream.close();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

		// Indicate end of program - Could be successful
		System.out.println("\nEnd of program.");
		System.out.println("----------------------------------------------------");

	}
	
	public static void main(String[] args) {
		// Declare file
		rainfallFile = "RainfallData_SimpangAmpat.txt";
		
		// Declare variable
		String[] rainfallDate = {"3/6/2023","4/6/2023","5/6/2023","6/6/2023","7/6/2023","8/6/2023"};
		int rainfallRate[] = {4,1,0,6,19,1};
		
		// Call method to generate rainfall data
		generateRainfallData(rainfallDate, rainfallRate);
		
		// Call method to read rainfall data
		readRainfallData();
		
		
		// Calculate average utlization
		String averageRainfallRate = String.format("%.1f", totalRainfallRate / noOfDays);
		System.out.print("\nAverage rainfall at Simpang Ampat for " + noOfDays + " days: " + averageRainfallRate + "%");
		

	}

}
