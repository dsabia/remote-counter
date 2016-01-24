package ie.greenfinch.test.graph.candidate.sabia.factory;

import ie.greenfinch.test.graph.candidate.sabia.util.GraphUtil;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class GraphFactory {

	/**
	 * Add edge into graph from an input string, es. AB5 connect vertex A to B with weight 5 
	 * @param graph
	 * @param inputSource
	 */
	public static void addEdge(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph,
			String inputSource) {
		
		// 1. validate
		validateInput(inputSource);
		
		// 2. split string and number
		String[] splittedInputSource = splitAndValidate(inputSource);
		
		// 3. check vertex to split the letter's part of the input
		String[] vertex = extractAndValidateVertex(graph, splittedInputSource[0]);
		
		// 4. add edge
		Double weight = Double.parseDouble(splittedInputSource[1]);
		GraphUtil.addEdge(graph,vertex[0], vertex[1], weight );
	}

	
	/*
	 * validate format input
	 */
	static void validateInput(String inputSource) {
		if(!inputSource.matches("[A-Z][A-Z]+[0-9]+")){
			throw new IllegalArgumentException(inputSource);
		}
	}

	/*
	 * split letter and number parts
	 */
	static String[] splitAndValidate(String inputSource) {
		String[] split = inputSource.split("(?=\\d)(?<=\\D)");
		if(split.length != 2){
			throw new IllegalArgumentException(inputSource);
		}
		return split;
	}


	/*
	 * return a list of 2 vertex validate in the graph.
	 * 
	 */
	static String[] extractAndValidateVertex(
			DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph,
			String lettersInputSource) {
		
		String currentWordChain = "";// cache variable
		String firstVertexDetected = "";
		String secondVertexDetected = "";
		
		for (Character c : lettersInputSource.toCharArray()) {
			
			currentWordChain += c;
			if(GraphUtil.checkGraphVertex(graph, currentWordChain)){
				if("".equals(firstVertexDetected)){
					firstVertexDetected = currentWordChain;
					currentWordChain = "";
				}else if(!"".equals(firstVertexDetected) && "".equals(secondVertexDetected)){
					secondVertexDetected = currentWordChain;
					currentWordChain = "";
				}
			}
			if(!"".equals(currentWordChain)){
				throw new IllegalArgumentException(lettersInputSource);
			}
		}
		if("".equals(firstVertexDetected) || "".equals(secondVertexDetected)){
			throw new IllegalArgumentException(lettersInputSource);
		}
		
		return new String[]{firstVertexDetected, secondVertexDetected};
	}
}
