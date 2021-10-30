import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class DirectedAcyclicGraphTest {
	
	@Test
	void testfindLCAInDAG() {
		/*
		 * Graph:
		 *        -> 4 -> 5
		 *       /
		 *   -> 1
		 *  /    \-> 3
		 * 0
		 *  \-> 2
		 * 
		 */
		DAG graph = new DAG();
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(4, 5);
		assertEquals("Should return 1", "1", getString(graph.findLCA(5, 3)));
	}
	
	@Test
	public void testConstructor() {
		DirectedNode node1 = new DirectedNode(1);
		DirectedNode node2 = new DirectedNode(2);
		DirectedNode node3 = new DirectedNode(3);
		assertEquals("Value is 1",1 ,node1.data);
		assertEquals("Should be 0",0 ,node1.getOutNodeAmount());
		assertEquals("Should be false",false ,node1.edgeExistsTo(node2));
		node1.addEdgeTo(node3);
		assertEquals("Should be true",true ,node1.edgeExistsTo(node3));
		assertEquals("Should be 1",1 ,node1.getOutNodeAmount());
	}

	@Test
	void testBinaryTree() {
		/*
		 * Graph:
		 *        -> 4
		 *       /
		 *   -> 1
		 *  /    \-> 3
		 * 0
		 *  \    /-> 5
		 *   -> 2
		 *       \-> 6
		 */
		DAG tree = new DAG();
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.addEdge(2, 5);
		tree.addEdge(2, 6);
        
        assertEquals("LCA 4,5 ", "0", getString(tree.findLCA(4, 5)));
        assertEquals("LCA 4,3 ", "1", getString(tree.findLCA(4, 3)));
        assertEquals("LCA 3,4 ", "1", getString(tree.findLCA(3, 4)));
        assertEquals("LCA 1,4 ", "1", getString(tree.findLCA(1, 4)));
	}
	
	@Test
	public void testDuplicateNode() {
		/*
		 * Graph:
		 *        -> 4
		 *       /
		 *   -> 1
		 *  /    \-> 3
		 * 0
		 *  \    /-> 5
		 *   -> 2
		 *       \-> 6
		 */
		DAG tree = new DAG();
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.addEdge(2, 5);
		tree.addEdge(2, 6);
        
		assertEquals("Should be 0", "0", getString(tree.findLCA(0, 0)));
        assertEquals("Should be 1", "1", getString(tree.findLCA(1, 1)));
        assertEquals("Should be 2", "2", getString(tree.findLCA(2, 2)));
        assertEquals("Should be 3", "3", getString(tree.findLCA(3, 3)));
        assertEquals("Should be 4", "4", getString(tree.findLCA(4, 4)));
	}
	
	@Test
	public void testLCAAsInputNode() {
		/*
		 * Graph:
		 *        -> 4
		 *       /
		 *   -> 1
		 *  /    \-> 3
		 * 0
		 *  \    /-> 5
		 *   -> 2
		 *       \-> 6
		 */
		DAG tree = new DAG();
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.addEdge(2, 5);
		tree.addEdge(2, 6);
        
        assertEquals("Should be 1", "1", getString(tree.findLCA(1, 4)));
        assertEquals("Should be 2", "2", getString(tree.findLCA(2, 5)));
	}
	
	@Test
	public void testNonExistantNode(){	
		/*
		 * Graph:
		 *        -> 4
		 *       /
		 *   -> 1
		 *  /    \-> 3
		 * 0
		 *  \    /-> 5
		 *   -> 2
		 *       \-> 6
		 */
		DAG tree = new DAG();
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.addEdge(2, 5);
		tree.addEdge(2, 6);
        
		assertEquals("Should be empty if 1 node does not exist","" , getString(tree.findLCA(3, 100)));
		assertEquals("Should be empty as it is the same node","" , getString(tree.findLCA(8, 8)));
		assertEquals("Should be empty if neither node exists","" , getString(tree.findLCA(40, 20)));
	}

	@Test
	void testCycleCyclicGraph() {
		/*
		 * Graph:
		 * 
		 * 0 -> 2 -> 3 -> 4
		 * v   ^          |
 	 	 *    /           |
		 * 1   <-----------
		 */
		DAG graph = new DAG();
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 1);
		assertEquals("Will return true as graph has a cycle", true, graph.hasCycle());
	}
	
	@Test
	void testCycleTreeGraph() {
		/*
		 * Graph:
		 *        -> 2 -> 0
		 *       /
		 *   -> 4
		 *  /    \-> 1
		 * 5
		 *  \-> 3
		 * 
		 */
		DAG graph = new DAG();
		graph.addEdge(5, 4);
		graph.addEdge(5, 3);
		graph.addEdge(4, 2);
		graph.addEdge(4, 1);
		graph.addEdge(2, 0);
		assertEquals("Will return false as graph has no cycles", false, graph.hasCycle());
		
	}
	
	@Test
	void testCycleDirectedAcyclicGraph() {
		/*
		 * Graph:
		 * 
		 * 0 -> 2 -> 3 -> 4
		 * v   ^
 	 	 *    /
		 * 1          5
		 */
		DAG graph = new DAG();
		graph.addEdge(0,  1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		assertEquals("Will return false as graph has no cycles", false, graph.hasCycle());
	}
	
	@Test
	void testCreateGraph() {
		/*
		 * Graph:
		 * 
		 * 0 -> 2 -> 3 -> 4
		 * v   ^
 	 	 *    /
		 * 1          5
		 */
		DAG graph = new DAG();
		graph.addEdge(0,  1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addNode(5);
		assertEquals("Amount of nodes should be 6", 6, graph.getNodeAmount());
		assertEquals("Amount of edges should be 5", 5, graph.getEdgeAmount());
		
		DirectedNode node;
		node = graph.getNode(0);
		assertEquals("Amount of out nodes should be 2", 2, node.getOutNodeAmount());
		
		node = graph.getNode(5);
		assertEquals("Amount of out nodes should be 0", 0, node.getOutNodeAmount());
		
		node = graph.getNode(3);
		assertEquals("Amount of out nodes should be 1", 1, node.getOutNodeAmount());
	}
	
	private String getString(int[] array) {
		String result = "";
		for(int i = 0; i < array.length; i++) {
			result += array[i];
			result += ", ";
		}
		if(result != "")
			result = result.substring(0, result.length() - 2);
		return result;
	}

}
