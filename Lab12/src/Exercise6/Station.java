package Exercise6;

public class Station {

	private int stationID;
	private String stationName;
	private String districtName;
	private int rainfallRate[];
	
	Station(int stationID, String stationName, String districtName, int[] rainfallRate){
		this.setStationID(stationID);
		this.setStationName(stationName);
		this.setDistrictName(districtName);
		this.setRainfallRate(rainfallRate);
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int[] getRainfallRate() {
		return rainfallRate;
	}

	public void setRainfallRate(int rainfallRate[]) {
		this.rainfallRate = rainfallRate;
	}
}
