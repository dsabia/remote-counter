package ie.greenfinch.test.graph.candidate.sabia.factory;

import ie.greenfinch.test.graph.candidate.sabia.test_util.TestUtil;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

/**
 * Unit test 
 * @author Daniel
 *
 */
public class GraphFactoryTest {
	
	@Test
	public void test_validateInput_ok(){
		String input = "AB2";
		GraphFactory.validateInput(input);
	}
	@Test
	public void test_validateInput_ok2(){
		String input = "AEDE24";
		GraphFactory.validateInput(input);
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_validateInput_void(){
		String input = "";
		GraphFactory.validateInput(input);
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_validateInput_incompleteParameter(){
		String input = "A2";
		GraphFactory.validateInput(input);
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_validateInput_notValidParameter(){
		String input = "2Ab";
		GraphFactory.validateInput(input);
	}

	
	@Test
	public void test_splitAndValidate_correctly(){
		String input = "AB2";
		GraphFactory.splitAndValidate(input);
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_splitAndValidate_inverse(){
		String input = "2AB";
		GraphFactory.splitAndValidate(input);
	}
	
	@Test
	public void test_extractAndValidateVertex(){
		String lettersInputSource = "AB";
		GraphFactory.extractAndValidateVertex(TestUtil.getInstanceGraphTestGreenfinch(), lettersInputSource );
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_extractAndValidateVertex_invalidInputExtracharacter(){
		String lettersInputSource = "ABC";
		GraphFactory.extractAndValidateVertex(TestUtil.getInstanceGraphTestGreenfinch(), lettersInputSource );
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_extractAndValidateVertex_invalidInputMissNode(){
		String lettersInputSource = "A";
		GraphFactory.extractAndValidateVertex(TestUtil.getInstanceGraphTestGreenfinch(), lettersInputSource );
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_extractAndValidateVertex_nodesNotInGraph(){
		String lettersInputSource = "FG";
		GraphFactory.extractAndValidateVertex(TestUtil.getInstanceGraphTestGreenfinch(), lettersInputSource );
	}
	@Test(expected=IllegalArgumentException.class)
	public void test_extractAndValidateVertex_oneNodesNotInGraph(){
		String lettersInputSource = "AG";
		GraphFactory.extractAndValidateVertex(TestUtil.getInstanceGraphTestGreenfinch(), lettersInputSource );
	}

}
