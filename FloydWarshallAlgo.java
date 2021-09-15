package asroche.hw5;

import algs.days.day26.FloydWarshallUndirected;
import algs.hw5.FloydWarshallSolutionAnimation;
import algs.hw5.map.HighwayMapWeighted;
import algs.hw5.map.WeightedInformation;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

/**
 * Question 2 of Homework 5.
 * 
 * Given the Massachusetts highway map data, find two vertices in the graph such that 
 * the shortest distance between them is greater than any other pair of vertices in the graph.
 * 
 * In other words, can you find two locations in Massachusetts such that using the available 
 * map data, you’ve computed the shortest total distance in terms of accumulated mileage, 
 * and no other pair of vertices demands a longer trip.
 */
public class Q2 {
	public static void main(String[] args) {
		WeightedInformation info = HighwayMapWeighted.undirectedGraph();
		EdgeWeightedGraph graph = info.ewgraph;

		System.out.println("Computing Floyd-Warshall: This might take up to a minute...");
		FloydWarshallUndirected fw = new FloydWarshallUndirected(graph);
		System.out.println("done");

		double shortestDist = Integer.MIN_VALUE;
		int from = 0;
		int to = 0;
		for(int i=0; i<graph.V(); i++)
		{
			for(int j=1; j<graph.V() && i!=j; j++)
			{
				if(fw.distTo(i, j) > shortestDist)
				{
					shortestDist = fw.distTo(i, j);
					from = i;
					to = j;

				}


			}
		}


		System.out.println("vertex1 is: " + info.labels.get(from) + " @ " + info.positions.get(from) );
		System.out.println("vertex2 is: " + info.labels.get(to) + " @ " + info.positions.get(to) );
		System.out.println("Total Mileage: " + shortestDist);
		
		


		// To visually animate the solution, properly use integer vertex endpoints. You know this is 
		// right when you see it!
		new FloydWarshallSolutionAnimation(fw).launch(to, from);
	}
}
