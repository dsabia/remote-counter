package ie.greenfinch.test.graph.candidate.sabia.util;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class GraphUtil {

	public static void addVertex(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph,
			String vertex){
		graph.addVertex(vertex);
	}
	
	public static boolean checkGraphVertex(
			DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph,
			String vertex) {
		return graph.containsVertex(vertex);
	}

	public static void addEdge(
			DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph,
			String startVertex, String endVertex, Double weight) {
		DefaultWeightedEdge edge = graph.addEdge(startVertex, endVertex);
		graph.setEdgeWeight(edge, weight);
	}

}
