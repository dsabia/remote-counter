package ie.greenfinch.test.graph.candidate.sabia.report;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

public class SummaryManager {
	
	private static final String NO_SUCH_RUTE = "NO SUCH RUTE";
	
	public SummaryVO summarizeShortestPathInfo(DijkstraShortestPath<String, DefaultWeightedEdge> dj){
		if(dj.getPath() == null){
			return new SummaryVO(NO_SUCH_RUTE);
		}
		Integer stops = dj.getPathEdgeList() == null ? 0 : dj.getPathEdgeList().size();
		Double weight = dj.getPathLength();
		
		return new SummaryVO( dj.getPathEdgeList() , weight ,stops);
	}
	
	public SummaryVO summarizeMultipleShortestPathInfo(List<DijkstraShortestPath<String, DefaultWeightedEdge>> djList){
		Integer stops = 0;
		Double weight = 0d;
		List<DefaultWeightedEdge> list = new ArrayList<DefaultWeightedEdge>();
		for (DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath : djList) {
			stops += dijkstraShortestPath.getPathEdgeList().size();
			weight += dijkstraShortestPath.getPathLength();
			list.addAll(dijkstraShortestPath.getPathEdgeList());
		}
		
		return new SummaryVO(list, weight, stops);
	}
}
