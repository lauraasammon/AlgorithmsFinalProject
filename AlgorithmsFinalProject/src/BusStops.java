public class BusStops 
{
	int stopID;
	String stopCode;
	String stopName;
	String stopDesc;
	double stopLat;
	double stopLon;
	String zoneID;
	String stopURL;
	String locationType;	
	public BusStops(int stopID, String stopCode, 
				    String stopName, String stopDesc, 
				    double stopLat, double stopLon, 
				    String zoneID, String stopURL, 
				    String locationType)
	{
		this.stopID = stopID;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.stopLat = stopLat;
		this.stopLon = stopLon;
		this.zoneID = zoneID;
		this.stopURL = stopURL;
		this.locationType = locationType;
	}
}
