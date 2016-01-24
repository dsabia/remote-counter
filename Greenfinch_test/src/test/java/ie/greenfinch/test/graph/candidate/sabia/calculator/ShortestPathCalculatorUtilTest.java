package ie.greenfinch.test.graph.candidate.sabia.calculator;

import ie.greenfinch.test.graph.candidate.sabia.test_util.TestUtil;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathCalculatorUtilTest {

	private ShortestPathCalculatorUtil shortestPathCalculatorUtil;
	
	@Before
	public void setUp() {
		shortestPathCalculatorUtil = new ShortestPathCalculatorUtil();
	}
	
	@Test 
	public void test_calculatePathOneVertexOnly(){
		String vertex = "B";
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = TestUtil.getInstanceGraphTestGreenfinch();
		DijkstraShortestPath<String, DefaultWeightedEdge> dj = shortestPathCalculatorUtil.calculateShortestPath(graph, vertex);
		Assert.assertNotEquals(dj.getPathLength(), 0d, 0.1d);
		Assert.assertFalse(graph.containsVertex(ShortestPathCalculatorUtil.START_NODE));
	}
}

