package ie.greenfinch.test.graph.candidate.sabia;

import ie.greenfinch.test.graph.candidate.sabia.calculator.ShortestPathCalculatorUtil;
import ie.greenfinch.test.graph.candidate.sabia.report.SummaryManager;
import ie.greenfinch.test.graph.candidate.sabia.report.SummaryVO;
import ie.greenfinch.test.graph.candidate.sabia.test_util.TestUtil;

import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Execute simulation of the test
 * @author Daniel
 *
 */

public class GreenFinchIntegrationTest {
	ShortestPathCalculatorUtil shortestPathCalculatorUtil;
	SummaryManager summaryManager;
	DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
	private double delta = 0.001;

	@Before
	public void setUp() {
		shortestPathCalculatorUtil = new ShortestPathCalculatorUtil();
		summaryManager = new SummaryManager();
		
		graph = TestUtil.getInstanceGraphTestGreenfinch();
	}
	
	/*
	 *Find the Distance...
	 */
	/**
	 * 1. Find the distance of the route A-B-C.
		print(formatShortestPathInfo(calculateShortestPath(graph, "A", "B")));
		print(formatShortestPathInfo(calculateShortestPath(graph, "B", "C"))); 
	 * @return
	 */
	@Test
	public void testDistance_ABC8(){
		List<DijkstraShortestPath<String, DefaultWeightedEdge>> list = shortestPathCalculatorUtil.calculateShortestPathMultipleNode(graph, new String[]{"A","B","C"});
		SummaryVO summaryVO = summaryManager.summarizeMultipleShortestPathInfo( list );
		
		Assert.assertEquals(summaryVO.getDistance(), 9d, delta);
	}
	
	@Test
	public void testDistance_AD5(){
		DijkstraShortestPath<String, DefaultWeightedEdge> dj = shortestPathCalculatorUtil.calculateShortestPath(graph,"A","D");
		SummaryVO summaryVO = summaryManager.summarizeShortestPathInfo( dj );
		
		Assert.assertEquals(summaryVO.getDistance(), 5d, delta);
	}
	
	@Test
	public void testDistance_ADC13(){
		List<DijkstraShortestPath<String, DefaultWeightedEdge>> list = shortestPathCalculatorUtil.calculateShortestPathMultipleNode(graph, new String[]{"A","D","C"});
		SummaryVO summaryVO = summaryManager.summarizeMultipleShortestPathInfo( list );
		
		Assert.assertEquals(summaryVO.getDistance(), 13d, delta);
	}
	
	@Test
	public void testDistance_AEBCD22(){
		List<DijkstraShortestPath<String, DefaultWeightedEdge>> list = shortestPathCalculatorUtil.calculateShortestPathMultipleNode(graph, new String[]{"A","E","B","C","D"});
		SummaryVO summaryVO = summaryManager.summarizeMultipleShortestPathInfo( list );
		
		Assert.assertEquals(summaryVO.getDistance(), 22d, delta);
	}
	
	@Test
	public void testDistance_AED22(){
		List<DijkstraShortestPath<String, DefaultWeightedEdge>> list = shortestPathCalculatorUtil.calculateShortestPathMultipleNode(graph, new String[]{"A","E","D"});
		SummaryVO summaryVO = summaryManager.summarizeMultipleShortestPathInfo( list );
		
		Assert.assertEquals(summaryVO.getDistance(), 22d, delta);
	}
	
	@Test
	public void testDistance_NOSUCHRUTE(){
		DijkstraShortestPath<String, DefaultWeightedEdge> dj = shortestPathCalculatorUtil.calculateShortestPath(graph, "E","A");
		SummaryVO summaryVO = summaryManager.summarizeShortestPathInfo( dj );
		
		Assert.assertEquals(summaryVO.getMessage(), "NO SUCH RUTE");
		Assert.assertEquals(summaryVO.toString(), "NO SUCH RUTE");
	}
	
	
	@Test
	public void testShortestRute_AC(){
		DijkstraShortestPath<String, DefaultWeightedEdge> dj = shortestPathCalculatorUtil.calculateShortestPath(graph, "A","C");
		SummaryVO summaryVO = summaryManager.summarizeShortestPathInfo( dj );
		
		Assert.assertEquals(summaryVO.getDistance(), 9d, delta);
	}

	@Test
	public void testShortestRute_BB(){
		DijkstraShortestPath<String, DefaultWeightedEdge> dj = shortestPathCalculatorUtil.calculateShortestPath(graph, "B","B");
		SummaryVO summaryVO = summaryManager.summarizeShortestPathInfo( dj );
		
		Assert.assertEquals(summaryVO.getDistance(), 9d, delta);
	}
}

