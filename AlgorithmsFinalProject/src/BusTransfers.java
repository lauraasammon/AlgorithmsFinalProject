public class BusTransfers 
{
	int fromStopID;
	int toStopID;
	String transferType;
	String minTransferTime;
	
	
	public BusTransfers(int fromStopID, int toStopID, 
				   		String transferType, String minTransferTime)
	{
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.transferType = transferType;
		this.minTransferTime = minTransferTime;
	}
}
