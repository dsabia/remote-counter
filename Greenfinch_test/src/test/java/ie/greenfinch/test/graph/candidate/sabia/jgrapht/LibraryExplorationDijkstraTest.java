package ie.greenfinch.test.graph.candidate.sabia.jgrapht;

import ie.greenfinch.test.graph.candidate.sabia.test_util.TestUtil;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Assert;
import org.junit.Test;

public class LibraryExplorationDijkstraTest {

	@Test
	public void test_dijkstraDetectSameSourceAndDestination(){
		DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<String, DefaultWeightedEdge>(TestUtil.getInstanceGraphTestGreenfinch(), "B", "B");
		Assert.assertEquals(dijkstraShortestPath.getPathLength(), 0d, 0d);
	}
}
