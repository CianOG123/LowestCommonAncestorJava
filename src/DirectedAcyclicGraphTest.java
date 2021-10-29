import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DirectedAcyclicGraphTest {
	
	@Test
	void testfindLCAInDAG() {
		//assertEquals("Should return x", x, graph.findLCA(y, z));
		//assertEquals("Should return x", x, graph.findLCA(y, z));
		//assertEquals("Should return x", x, graph.findLCA(y, z));
	}
	
	// Rewrite to work for directed graph ------------------------------------------------
	
	@Test
	public void testConstructor() {
		Node root = new Node(0);
		assertEquals("Value is 0",0 ,root.data);
		assertEquals("Left node is null",null ,root.left);
		assertEquals("Right node is null",null ,root.right);
	}

	@Test
	void testBinaryTree() {
		BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        
        assertEquals(tree.findLCA(4,5), 2, "LCA 4,5 ");
        assertEquals(tree.findLCA(4,6), 1, "LCA 4,6 ");
        assertEquals(tree.findLCA(3,4), 1, "LCA 3,4 ");
        assertEquals(tree.findLCA(2,4), 2, "LCA 2,4 ");
	}
	
	@Test
	public void testDuplicateNode() {
		BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        
        assertEquals("Should be 1", 1, tree.findLCA(1, 1));
        assertEquals("Should be 4", 4, tree.findLCA(4, 4));
        assertEquals("Should be 6", 6, tree.findLCA(6, 6));
	}
	
	@Test
	public void testLCAAsInputNode() {
		BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        
        assertEquals("Should be 1", 1, tree.findLCA(1, 4));
        assertEquals("Should be 2", 2, tree.findLCA(2, 5));
	}
	
	@Test
	public void testNonExistantNode(){	
		BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        
		assertEquals("Should be -1 if 1 node does not exist",-1 ,tree.findLCA(3, 100));
		assertEquals("Should be -1 as it is the same node",-1 ,tree.findLCA(8, 8));
		assertEquals("Should be -2 if neither node exists",-1,tree.findLCA(40, 20));
	}
	
	// -----------------------------------------------------------------------------------

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
		Graph graph = new Graph();
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
		Graph graph = new Graph();
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
		Graph graph = new Graph();
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
		Graph graph = new Graph();
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

}
