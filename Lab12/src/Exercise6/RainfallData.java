package Exercise6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RainfallData extends Station{

	private static String rainfallFile;
	private static String rainfallDate[] = {"3/6/2023","4/6/2023","5/6/2023","6/6/2023","7/6/2023","8/6/2023"};
	private static int totalRainfallMelaka;
	
	RainfallData(int stationID, String stationName, String districtName, int[] rainfallRate) {
		super(stationID, stationName, districtName, rainfallRate);
		
	}
	
	// Method to calculate average of rainfall rate for 6 days/locations
	public static double calculateAverage(int totalRainfallRate){
		double averageRainfall = totalRainfallRate/6;
		return averageRainfall;
	}
	
	// Method to generate rainfall data
	public static void writeRainfallData(DataOutputStream dataStream, Station station) {
		try {
			// Process data
			dataStream.writeInt(station.getStationID());
			dataStream.writeUTF(station.getStationName());
			dataStream.writeUTF(station.getDistrictName());
			for (int index = 0; index < station.getRainfallRate().length; index++) {
				// Write data into data stream
				dataStream.writeInt(station.getRainfallRate()[index]);
			}
			// Flush for each writing
			dataStream.flush();
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	// Method to read rainfall data
	public static void readRainfallData() {
		System.out.println("\nThe following are the rainfall rate at Melaka:\n");
		try {

			// Create stream to read data
			DataInputStream dataStream = new DataInputStream(new FileInputStream(rainfallFile));
			
			String stationName = "", districtName = "";
			int rainfallRate[] = {0,0,0,0,0,0}; 
			int stationID = 0;
			totalRainfallMelaka = 0;
			// Process data until end-of-file
			while(dataStream.available() > 0) {
				
				// Read data
				stationID = dataStream.readInt();
				stationName = dataStream.readUTF();
				districtName = dataStream.readUTF();
				for (int index = 0; index < rainfallDate.length; index++) {
					rainfallRate[index] = dataStream.readInt();
				}
				int totalRainfallRate = 0;
				System.out.println("Station ID: "+ stationID + "\t Station Name: " + stationName +"\t District: " + districtName);
				for (int index = 0; index <  rainfallDate.length; index++) {
					System.out.println(rainfallDate[index] + "\t" + rainfallRate[index]);
					// Calculate total rainfall rate
					totalRainfallRate += rainfallRate[index];
				}
				totalRainfallMelaka+=totalRainfallRate;
				System.out.println("\nAverage rainfall rate for this location:"+ String.format("%.1f", calculateAverage(totalRainfallRate)) +"\n\n");
			}
			
			// Close stream
			dataStream.close();
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

		// Indicate end of program - Could be successful
		System.out.println("\nEnd of read data program.");
		System.out.println("----------------------------------------------------");

	}

	public static void main(String[] args) {
		// Declare file
		rainfallFile = "RainfallData_Melaka.txt";
		
		// Variable
		int rainfallDurianTunggal[] = {0,0,0,37,6,9};
		int rainfallSimpangAmpat[] = {4,1,0,6,19,1};
		int rainfallTelokRimba[] = {0,1,1,69,53,4};
		int rainfallChohong[] = {0,21,0,39,14,24};
		int rainfallSgDuyong[] = {0,3,0,43,26,26};
		int rainfallCheng[] = {0,3,0,42,10,14};
		
		// Declare object
		Station stationDurianTunggal = new Station(2222002, "Durian Tunggal", "Alor Gajah", rainfallDurianTunggal);
		Station stationSimpangAmpat = new Station(2421003, "Simpang Ampat", "Alor Gajah", rainfallSimpangAmpat);
		Station stationTelokRimba = new Station(2125002, "Telok Rimba", "Jasin", rainfallTelokRimba);
		Station stationChohong = new Station(2225044, "Chohong", "Jasin", rainfallChohong);
		Station stationSgDuyong = new Station(2223023, "Sg. Duyong", "Melaka Tengah", rainfallSgDuyong);
		Station stationCheng = new Station(2222006, "Cheng", "Melaka Tengah", rainfallCheng);
		
		
		try {
			// Create stream to write data
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(rainfallFile));
			
			writeRainfallData(dataOutputStream, stationDurianTunggal);
			writeRainfallData(dataOutputStream, stationSimpangAmpat);
			writeRainfallData(dataOutputStream, stationTelokRimba);
			writeRainfallData(dataOutputStream, stationChohong);
			writeRainfallData(dataOutputStream, stationSgDuyong);
			writeRainfallData(dataOutputStream, stationCheng);
			
			// Close stream
			dataOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Indicate end of program - Could be successful
		System.out.println("Check out " + rainfallFile+ " file. \n\nEnd of write data program."); 
		System.out.println("----------------------------------------------------");
		
		readRainfallData();
		
		System.out.println("Average rainfall rate in Melaka: "+calculateAverage(totalRainfallMelaka));
		
		
	}

}
