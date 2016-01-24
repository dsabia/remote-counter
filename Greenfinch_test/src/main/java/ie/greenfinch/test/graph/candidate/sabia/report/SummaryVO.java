package ie.greenfinch.test.graph.candidate.sabia.report;

import java.util.List;

import org.jgrapht.graph.DefaultWeightedEdge;

public class SummaryVO {
	
	private List<DefaultWeightedEdge> list;
	private Double distance;
	private Integer stops;
	private String message;
	
	public SummaryVO(String message) {
		super();
		this.message = message;
	}

	public SummaryVO(List<DefaultWeightedEdge> list, Double distance,
			Integer stops) {
		super();
		this.list = list;
		this.distance = distance;
		this.stops = stops;
	}
	
	public List<DefaultWeightedEdge> getList() {
		return list;
	}
	public Double getDistance() {
		return distance;
	}
	public Integer getStops() {
		return stops;
	}
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		if(!"".equals(message)){
			return message;
		}
		return list + " =>   weight : " + distance + "  stops:" + stops;
	}
}
