package ie.greenfinch.test.graph.candidate.sabia.test_util;

import ie.greenfinch.test.graph.candidate.sabia.factory.GraphFactory;
import ie.greenfinch.test.graph.candidate.sabia.util.GraphUtil;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestUtil {
	
	public static DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> getInstanceGraphTestGreenfinch() {
		return createGraphTestGreenfinch();
	}
	
	private static DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> createGraphTestGreenfinch() {
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		GraphUtil.addVertex(graph, "A");
		GraphUtil.addVertex(graph, "B");
		GraphUtil.addVertex(graph, "C");
		GraphUtil.addVertex(graph, "D");
		GraphUtil.addVertex(graph, "E");
		
		//AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		GraphFactory.addEdge(graph, "AB5");
		GraphFactory.addEdge(graph, "BC4");
		GraphFactory.addEdge(graph, "CD8");
		GraphFactory.addEdge(graph, "DC8");
		GraphFactory.addEdge(graph, "DE6");
		GraphFactory.addEdge(graph, "AD5");
		GraphFactory.addEdge(graph, "CE2");
		GraphFactory.addEdge(graph, "EB3");
		GraphFactory.addEdge(graph, "AE7");
		
		return graph;
	}
}
