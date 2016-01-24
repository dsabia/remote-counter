package ie.greenfinch.test.graph.candidate.sabia.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * 
 * @author Daniel
 *
 */
public class ShortestPathCalculatorUtil {

	static final String START_NODE = "StartNode";
	/**
	 * Shortest path with 2 nodes
	 * @param graph
	 * @param v1
	 * @param v2
	 * @return
	 */
	public DijkstraShortestPath<String, DefaultWeightedEdge> calculateShortestPath(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph, String v1, String v2){
		if(v1.equals(v2)){
			return calculateShortestPath(graph, v1);
		}
		return new DijkstraShortestPath<String, DefaultWeightedEdge>(graph, v1, v2);
	}
	
	/**
	 * Shortest path with 1 nodes, force calculate the trip with temporary StartNode. 
	 * FIX: No thread safe.
	 * @param graph
	 * @param vertex
	 * @return
	 */
	public DijkstraShortestPath<String, DefaultWeightedEdge> calculateShortestPath(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph, String vertex) {

		graph.addVertex(START_NODE);
		Set<DefaultWeightedEdge> edgeOfB = graph.outgoingEdgesOf(vertex);
		for (DefaultWeightedEdge edge : edgeOfB) {
			String target = graph.getEdgeTarget(edge);
			DefaultWeightedEdge edgeTemporary = graph.addEdge(START_NODE, target);
			graph.setEdgeWeight(edgeTemporary, graph.getEdgeWeight(edge));
		}
		DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath4 = new DijkstraShortestPath<String, DefaultWeightedEdge>(graph, START_NODE, vertex);
		graph.removeVertex(START_NODE);
		return dijkstraShortestPath4;
	}
	
	/**
	 * Shortest path with intermediate stop => multiple calculation of dijkstra path. 
	 * @param graph
	 * @param nodes
	 * @return
	 */
	public List<DijkstraShortestPath<String, DefaultWeightedEdge>> calculateShortestPathMultipleNode(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph, String ... nodes){
		if(nodes.length <= 1 ){
			throw new IllegalArgumentException();
		}
		List<DijkstraShortestPath<String, DefaultWeightedEdge>> list = new ArrayList<DijkstraShortestPath<String,DefaultWeightedEdge>>();
		for (int i = 0; i < nodes.length -1; i++) {
			list.add(calculateShortestPath(graph, nodes[i], nodes[i+1]));
		}
		return list;
	}
}
