import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSystem 
{

	public static void main(String[] args) 
	{
		ArrayList<BusStops> busStops = new ArrayList<BusStops>();
		ArrayList<StopTimes> stopTimes = new ArrayList<StopTimes>();
		ArrayList<BusTransfers> busTransfers = new ArrayList<BusTransfers>();
		
		//ReadingInStops
		try
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
			String[] parseLine;
			
			File file = new File("/Users/laurasammon/Documents/College/TCD Year 2/Semester 2/Algorithms/FinalProject/InputFiles/stops.txt");
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); //First line in text file contains the names of the different data categories in the file.
			scanner.nextLine(); //Blank line in text file.
			while (scanner.hasNextLine())//First line with actual data in it in the text file.
			{
				parseLine = scanner.nextLine().split(",");
				stopID = Integer.parseInt(parseLine[0]);
				stopCode = parseLine[1];
				stopName = parseLine[2];
				stopDesc = parseLine[3];
				stopLat = Double.parseDouble(parseLine[4]);
				stopLon = Double.parseDouble(parseLine[5]);
				zoneID = parseLine[6];
				stopURL =  parseLine[7];
				locationType = parseLine[8];
				busStops.add(new BusStops(stopID, stopCode, 
						stopName, stopDesc, 
						stopLat, stopLon, 
						zoneID, stopURL, 
						locationType));
				
			}
			scanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		ArrayList<Integer> stopArray = new ArrayList<Integer>();
		for(int i = 0; i < busStops.size(); i++)
		{
			stopArray.add(busStops.get(i).stopID);
		}
		
		//ReadingInStopTimes
		try
		{	
			int tripID;
			String arrivalTime;
			String departureTime;
			int stopID;
			int stopSequence;
			String stopHeadsign;
			int pickupType;
			int dropOffType;
			String[] parseLine;
			
			File file = new File("/Users/laurasammon/Documents/College/TCD Year 2/Semester 2/Algorithms/FinalProject/InputFiles/stop_times.txt");
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); //First line in text file contains the names of the different data categories in the file.
			while (scanner.hasNextLine())//First line with actual data in it in the text file.
			{
				parseLine = scanner.nextLine().split(",");
				tripID = Integer.parseInt(parseLine[0]);
				arrivalTime = parseLine[1];
				departureTime = parseLine[2];
				stopID = Integer.parseInt(parseLine[3]);
				stopSequence = Integer.parseInt(parseLine[4]);
				stopHeadsign = parseLine[5];
				pickupType = Integer.parseInt(parseLine[6]);
				dropOffType = Integer.parseInt(parseLine[7]);
				stopTimes.add(new StopTimes(tripID, arrivalTime, 
						departureTime, stopID, 
						stopSequence, stopHeadsign, 
						pickupType, dropOffType));
			}
			scanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		//ReadingInTransfers
		try
		{	
			int fromStopID;
			int toStopID;
			String transferType;
			String minTransferTime;
			String[] parseLine;
			
			File file = new File("/Users/laurasammon/Documents/College/TCD Year 2/Semester 2/Algorithms/FinalProject/InputFiles/transfers.txt");
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); //First line in text file contains the names of the different data categories in the file.
			while (scanner.hasNextLine())//First line with actual data in it in the text file.
			{
				parseLine = scanner.nextLine().split(",");
				fromStopID = Integer.parseInt(parseLine[0]);
				toStopID = Integer.parseInt(parseLine[1]);
				transferType = parseLine[2];
				if(3 >= parseLine.length)
				{
					minTransferTime = "0";
				}
				else
				{
					minTransferTime = parseLine[3];
				}
				busTransfers.add(new BusTransfers(fromStopID, toStopID, 
						transferType, minTransferTime));
			}
			scanner.close();
		}
		catch(Exception e)
		{
			
		}
	
		//Sorting stop names
		TST tst = new TST();
		for(int i = 0; i < busStops.size(); i++)
		{
			String currentStopName = busStops.get(i).stopName;
			String[] parseStopName = currentStopName.split(" ");
			String sortedStopName = "";
			
			ArrayList<String> prefixes = new ArrayList<String>();
			prefixes.add("EB");
			//prefixes.add("FLAGSTOP");
			prefixes.add("NB");
			prefixes.add("SB");
			prefixes.add("WB");
			
			String beginningOfName = parseStopName[0];
			if(prefixes.contains(beginningOfName))
			{
				for(int j = 1; j < prefixes.size(); j++)
				{
					sortedStopName += parseStopName[j] + " ";
				}
				sortedStopName += beginningOfName;
				busStops.get(i).stopName = sortedStopName;
			}
			else
			{
				sortedStopName = currentStopName;
				busStops.get(i).stopName = sortedStopName;
			}
		}
		
		//Start of user interface
		boolean finished = false;
		while(!finished)
		{
			System.out.println("Welcome to the Vancouver Bus System.");
			System.out.println("Please enter the relevant input for the action you wish to take.");
			System.out.println("1. Find the shortest path between two bus stops. [Input = 1]");
			System.out.println("2. Search for a bus stop within our database. [Input = 2]");
			System.out.println("3. Search for all trips at a given time within our database. [Input = 3]");
			System.out.println("4. Exit the Vancouver Bus System. [Input = exit]");
			Scanner choiceScanner = new Scanner(System.in);
			
			if(choiceScanner.hasNext("exit"))
			{
				System.out.print("Thank you for using the Vancouver Bus System. Have a nice day.");
				System.exit(0);
			}
			else if(choiceScanner.hasNext("1"))
			{
				System.out.println("Please enter the stop IDs of the two stops you wish to find the shortest path between, separated by a comma only. (Enter 'back' to return to the main menu or 'exit' to exit the system.)");
				Scanner scan1 = new Scanner(System.in);
				
				if(scan1.hasNext("back"))
				{
					finished = false;
				}
				else if(scan1.hasNext("exit"))
				{
					System.out.print("Thank you for using the Vancouver Bus System. Have a nice day.");
					finished = true;
					System.exit(0);
				}
				else 
				{
					boolean complete1 = false;
					while(!complete1)
					{
						String userInput = scan1.next();
						String[] parseInput = userInput.split(",");
						int stopA = Integer.parseInt(parseInput[0]);
						int stopB = Integer.parseInt(parseInput[1]);
						if(stopArray.contains(stopA) && stopArray.contains(stopB) && parseInput.length == 2)
						{
							System.out.println("Calculating shortest path...");
							//counting number of bus stops to add to digraph and creates new edge weighted digraph
							int countVertices = 0;
							for(int l = 0; l < busStops.size(); l++)
							{
								countVertices++;
							}
							EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(countVertices);
							
							//creates edges with specified weights for stops in transfers.txt
							for(int a = 0; a < busTransfers.size(); a++)
							{
								for(int b = 1; b < busTransfers.size(); b++)
								{
									if(busTransfers.get(a).transferType == "2")
									{
										DirectedEdge edge = new DirectedEdge(busTransfers.get(a).fromStopID, busTransfers.get(b).toStopID, Integer.parseInt(busTransfers.get(a).minTransferTime)/100);
										digraph.addEdge(edge);
									}
									else if(busTransfers.get(a).transferType == "0")
									{
										DirectedEdge edge = new DirectedEdge(busTransfers.get(a).fromStopID, busTransfers.get(b).toStopID, 2);
										digraph.addEdge(edge);
									}
								}
							}
							ArrayList<String> stopNames = new ArrayList<String>();
							for(int i = 0; i < busStops.size(); i++)
							{
								stopNames.add(busStops.get(i).stopName);
							}
							
							//creates edges with specified weights for consecutive stops in stop_times.txt
							for(int k = 0; k < stopTimes.size()-1; k++)
							{
								if(stopTimes.get(k).tripID == stopTimes.get(k+1).tripID)
								{
									int vertex1 = stopNames.indexOf(k);
									int vertex2 = stopNames.indexOf(k+1);
									if(vertex1 < 0 || vertex2 < 0)
									{
										
									}
									else
									{
										DirectedEdge edge = new DirectedEdge(vertex1, vertex2,1);
										digraph.addEdge(edge);	
									}
								}	
								else
								{
									
								}
							}
							DijkstraSP shortestPath = new DijkstraSP(digraph, stopA);
							if(shortestPath.hasPathTo(stopB))
							{
								System.out.println("The shortest path between stops " + stopA + " and " + stopB + " is " + shortestPath.distTo(stopB) + ".");
							}
							else
							{
								System.out.println("There is no path between " + stopA + " and " + stopB + ".");
							}
							complete1 = true;
							scan1.close();
							System.out.println("Thank you for using the Vancouver Bus System. Have a nice day.");
							System.exit(0);
						}
						else
						{
							System.out.println("Input error! The stops entered were invalid. Please try again.");
							complete1 = false;
						}
						
					}		
				}
					
			}
			else if(choiceScanner.hasNext("2"))
			{
				System.out.println("Please enter, by full name or by the first few characters, the bus stop you wish to search for. (Enter 'back' to return to the main menu or 'exit' to exit the system.)");
				Scanner scan2 = new Scanner(System.in);
				
				if(scan2.hasNext("back"))
				{
					finished = false;
				}
				else if(scan2.hasNext("exit"))
				{
					System.out.print("Thank you for using the Vancouver Bus System. Have a nice day.");
					finished = true;
					System.exit(0);
				}
				else 
				{
					boolean complete2 = false;
					while(!complete2)
					{
						String userInput = scan2.nextLine();
						for(int k = 0; k < busStops.size(); k++)
						{
							tst.put(busStops.get(k).stopName, busStops.get(k));
						}
						Iterable<String> potentialMatches = tst.keysWithPrefix(userInput.toUpperCase());
						ArrayList<String> listOfConfirmedMatches = new ArrayList<String>();
						
						for(String match : potentialMatches)
						{
							listOfConfirmedMatches.add(match);
						}
						
						if(listOfConfirmedMatches.isEmpty())
						{
							System.out.println("Input error! The stop entered does not exist. Please try again.");
							complete2 = false;
						}
						else
						{
							for(String stop : listOfConfirmedMatches)
							{
								BusStops currentStop = (BusStops)tst.get(stop);
								System.out.println("Stop ID: " + currentStop.stopID);
								System.out.println("Stop Code: " + currentStop.stopCode);
								System.out.println("Stop Name: " + currentStop.stopName);
								System.out.println("Stop Desc: " + currentStop.stopDesc);
								System.out.println("Stop Lat: " + currentStop.stopLat);
								System.out.println("Stop Lon: " + currentStop.stopLon);
								System.out.println("Zone ID: " + currentStop.zoneID);
								System.out.println("Stop URL: " + currentStop.stopURL);
								System.out.println("Location Type: " + currentStop.locationType);
								System.out.println(" ");
							}
							complete2 = true;
							scan2.close();
							System.out.println("Thank you for using the Vancouver Bus System. Have a nice day.");
							choiceScanner.close();
							System.exit(0);	
						}
					}
				}												
			}
			else if(choiceScanner.hasNext("3"))
			{
				System.out.println("Please enter the arrival time for the trip you wish to search for in the following format: hh:mm:ss. (Enter 'back' to return to the main menu or 'exit' to exit the system.)");
				Scanner scan3 = new Scanner(System.in);
				
				if(scan3.hasNext("back"))
				{
					finished = false;
				}
				else if(scan3.hasNext("exit"))
				{
					System.out.print("Thank you for using the Vancouver Bus System. Have a nice day.");
					finished = true;
					System.exit(0);
				}
				else 
				{
					boolean complete3 = false;
					while(!complete3)
					{
						String userInput = scan3.next();
						String[] parseInput = userInput.trim().split(":");
						if(parseInput.length > 3 || parseInput.length < 3)
						{
							System.out.println("Input error! Please enter the arrival time in the format hh:mm:ss");
						}	
						else
						{
							int hour = Integer.parseInt(parseInput[0]);
							int minute = Integer.parseInt(parseInput[1]);
							int second = Integer.parseInt(parseInput[2]);
							if(hour < 0 || hour > 23)
							{
								System.out.println("Input error! There are only 24 hours in a day.");
							}
							else if(minute < 0 || minute > 59)
							{
								System.out.println("Input error! There are only 60 minutes in an hour.");
							}
							else if(second < 0 || second > 59)
							{
								System.out.println("Input error! There are only 60 seconds in a minute.");	
							}
							else
							{
								for(int i = 0; i < stopTimes.size(); i++)
								{
									if(userInput.equals(stopTimes.get(i).arrivalTime))
									{
										System.out.println("Trip ID: " + stopTimes.get(i).tripID);
										System.out.println("Arrival Time: " + stopTimes.get(i).arrivalTime);
										System.out.println("Departure Time: " + stopTimes.get(i).departureTime);
										System.out.println("Stop ID: " + stopTimes.get(i).stopID);
										System.out.println(" ");
									}
								}
								complete3 = true;
								scan3.close();
								System.out.println("Thank you for using the Vancouver Bus System. Have a nice day.");
								System.exit(0);
							}
						}
					}					
				}
			}			
		}
	}//end of main
}
