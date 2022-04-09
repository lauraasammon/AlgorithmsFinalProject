public class StopTimes 
{
	int tripID;
	String arrivalTime;
	String departureTime;
	int stopID;
	int stopSequence;
	String stopHeadsign;
	int pickupType;
	int dropOffType;
	public StopTimes(int tripID, String arrivalTime, 
					 String departureTime, int stopID, 
				     int stopSequence, String stopHeadsign, 
				     int pickupType, int dropOffType)
	{
		this.tripID = tripID;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopID = stopID;
		this.stopSequence = stopSequence;
		this.stopHeadsign = stopHeadsign;
		this.pickupType = pickupType;
		this.dropOffType = dropOffType;
	}
}
