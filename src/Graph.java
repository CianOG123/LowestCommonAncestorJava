import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<DirectedNode> nodes = new ArrayList<DirectedNode>();
	private int edges = 0;
	
	public boolean hasCycle() {
		List<Integer> visitedNodes = new ArrayList<Integer>();
		for(int i = 0; i < nodes.size(); i++) {
			DirectedNode root = nodes.get(i);
			if(hasCycleRecursive(visitedNodes, root))
				return true;
		}
		return false;
	}
	
	public boolean hasCycleRecursive(List<Integer> visitedNodes, DirectedNode node) {
		if(visitedNodes.contains(node.data))
			return true;
		visitedNodes.add(node.data);
		for(int i = 0; i < node.getOutNodeAmount(); i++) {
			DirectedNode iNode = node.outNodes.get(i);
			if(hasCycleRecursive(visitedNodes, iNode))
				return true;
		}
		visitedNodes.remove((Integer) node.data);
		return false;
	}

	public void addNode(int data) {
		DirectedNode node = getNode(data);
		initialiseNode(node, data);
	}

	public void addEdge(int fromData, int toData) {
		DirectedNode fromNode = getNode(fromData);
		DirectedNode toNode = getNode(toData);
		toNode = initialiseNode(toNode, toData);
		fromNode = initialiseNode(fromNode, fromData);
		if (!fromNode.edgeExistsTo(toNode)) {
			fromNode.addEdgeTo(toNode);
			edges++;
		}
	}

	private DirectedNode initialiseNode(DirectedNode node, int data) {
		if (node == null) {
			node = new DirectedNode(data);
			nodes.add(node);
		}
		return node;
	}

	// Returns node if found, else returns null
	public DirectedNode getNode(int data) {
		DirectedNode node = null;
		for (int i = 0; i < nodes.size(); i++) {
			DirectedNode indexNode = nodes.get(i);
			if (data == indexNode.data)
				node = indexNode;
		}
		return node;
	}

	public int getEdgeAmount() {
		return edges;
	}

	public int getNodeAmount() {
		return nodes.size();
	}
}
