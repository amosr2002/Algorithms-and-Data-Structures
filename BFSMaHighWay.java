package asroche.hw4;

import algs.days.day20.DepthFirstPaths;
import algs.days.day21.BreadthFirstPaths;
import algs.hw4.map.*;
import edu.princeton.cs.algs4.Graph;

/**
 * The goal of this question is to:
 * 
 * 1. Find the western-most location in Massachusetts
 * 2. Find the eastern-most location in Massachusetts
 * 3. Determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT
 * 4. Next create a copy of the highway graph that removes all line segments from I-90, the 
 *    Massachusetts Turnpike toll road.
 * 5. From this copy, determine the shortest distance between these two locations IN TERMS OF THE 
 *    TOTAL NUMBER OF HIGHWAY EDGES USED. YOU ARE NOT YET READY TO TAKE MILEAGE INTO ACCOUNT.
 */
public class Q2 {

	/**
	 * This method must create a copy of the graph, which you can do by recreate a graph with 
	 * the same number of vertices as the original one, BUT you only add an edge to the copy
	 * if the edge in the original graph IS NOT EXCLUSIVELY a line segment from the Mass Pike.
	 * 
	 * For example, in the data set you will see two nodes:
	 * 
	 * 		I-90@49|MA 42.169702 -72.580876
	 * 		I-90@51|MA 42.161558 -72.541995
	 * 
	 * These lines correspond to vertex #639 (the first one @49) and vertex #641 (the second one @51).
	 * Because the label for both of these vertices includes "I-90@" this edge must not appear in 
	 * the copied graph, since it is a highway segment exclusively on the Mass Turnpike.
	 * 
	 * Note that the edge is eliminated only when BOTH are present. For example, the following
	 * line segment will remain:
	 * 
	 * 		I-95(23)/MA128	                ==> vertex #705
	 * 		I-90@123B&I-95@24&MA128@24(95)  ==> vertex #1785
	 */
	static Information remove_I90_segments(Information info) {
		Graph copy = null;
		copy = new Graph(info.graph.V());
		
		for(int i=0; i<info.graph.V(); i++)
		{
		
			
			//Boolean[] marked = new Boolean[info.graph.V()];
			
			
			//marked[i] = true;
			for(int j:info.graph.adj(i))
			{	
				

				if (info.labels.get(j).contains("I-90@") && info.labels.get(i).contains("I-90@"))

				{
					continue;
				}
				
				
				if(i<j)
					copy.addEdge(i, j);

				
				
				

			}
		}

		Information newInfo = new Information(copy, info.positions, info.labels);
		return newInfo;
	}


	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int westernMostVertex(Information info) {
		double longitude = Integer.MAX_VALUE;
		int westernVertex = 0;
		for(int id:info.labels.keys())
		{
			GPS gps = info.positions.get(id);

			if(gps.longitude < longitude)
			{
				longitude = gps.longitude;
				westernVertex = id;
			}


		}

		return westernVertex;
	}

	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int easternMostVertex(Information info) {

		double longitude = Integer.MIN_VALUE;
		int easternVertex = 0;
		for(int id:info.labels.keys())
		{
			GPS gps = info.positions.get(id);

			if(gps.longitude > longitude)
			{
				longitude = gps.longitude;
				easternVertex = id;
			}


		}

		return easternVertex;
	}

	/** 
	 * This helper method returns the southern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int southernMostVertex(Information info) {
		double latitude = Integer.MAX_VALUE;
		int southernVertex = 0;
		for(int id:info.labels.keys())
		{
			GPS gps = info.positions.get(id);

			if(gps.latitude < latitude)
			{
				latitude = gps.latitude;
				southernVertex = id;
			}


		}

		return southernVertex;
	}

	/** 
	 * This helper method returns the northern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int northernMostVertex(Information info) {
		double latitude = Integer.MIN_VALUE;
		int northernVertex = 0;
		for(int id:info.labels.keys())
		{
			GPS gps = info.positions.get(id);

			if(gps.latitude > latitude)
			{
				latitude = gps.latitude;
				northernVertex = id;
			}


		}

		return northernVertex;
	}

	public static void main(String[] args) {

		Information info = HighwayMap.undirectedGraph();
		
		int north = northernMostVertex(info);
		int south = southernMostVertex(info);
		int east = easternMostVertex(info);
		int west = westernMostVertex(info);
		
		System.out.println("eastern most vertex: " + east + " - > " + info.labels.get(east));
		System.out.println("western most vertex: " + west + " - > " + info.labels.get(west));
		System.out.println("northern most vertex: " + north + " - > " + info.labels.get(north));
		System.out.println("southern most vertex: " + south + " - > " + info.labels.get(south));
		//Now we have to find the shortest path from the western most vertex to eastern most vertex
		BreadthFirstPaths bp = new BreadthFirstPaths(info.graph, west);
		BreadthFirstPaths bp2 = new BreadthFirstPaths(info.graph, south);

		DepthFirstPaths dp = new DepthFirstPaths(info.graph, west);
		DepthFirstPaths dp2 = new DepthFirstPaths(info.graph, south);

		int counter = 0;
		System.out.println("Shortest path from west to east");
		for(int i:bp.pathTo(east))
		{
			counter++;
			System.out.println(i + " - > " + info.labels.get(i));
		}
		
		System.out.println(" ");
		System.out.println("----------------------------------------------------------------------------");

		System.out.println("Shortest path from south to north");
		int counter1 = 0;
		for(int i:bp2.pathTo(north))
		{
			counter1++;
			System.out.println(i + " - > " + info.labels.get(i));
		}
		

		int counter2 = 0;
		for(int i:dp.pathTo(east))
		{
			counter2++;

		}
		


		int counter3 = 0;
		for(int i:dp2.pathTo(north))
		{
			counter3++;

		}
		



		Information info2 = remove_I90_segments(info);



	
		

		
	
		

		

		BreadthFirstPaths bfp = new BreadthFirstPaths(info2.graph, west);
		BreadthFirstPaths bfp2 = new BreadthFirstPaths(info2.graph, south);
	
		int counter4 = 0;
		for(int i:bfp.pathTo(east))
		{
			counter4++;

		}
		System.out.println("There are " + (counter4-1) + " Edges in a breadth first search from the western most vertex to the eastern most vertex after we remove the I-90 segments");
		
		int counter5 = 0;
		for(int i:bfp2.pathTo(north))
		{
			counter5++;

		}
		System.out.println("There are " + (counter5-1) + " Edges in a breadth first search from the southern most vertex to the northern most vertex after we remove the I-90 segments");
	
		
		System.out.println("There are " + (counter-1) + " Edges in the shortest path from the western most vertex to the eastern most vertex");
		System.out.println("There are " + (counter1-1) + " Edges in the shortest path from the southern most vertex to the northern most vertex");
		System.out.println("There are " + (counter2-1) + " Edges in a depth first search from the western most vertex to the eastern most vertex");
		System.out.println("There are " + (counter3-1) + " Edges in a depth first search from the southern most vertex to the northern most vertex");
		
	}
}
