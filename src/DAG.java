import java.util.ArrayList;
import java.util.List;

public class DAG {

	private List<DirectedNode> nodes = new ArrayList<DirectedNode>();
	private int edges = 0;

	public int[] findLCA(int node1, int node2) {
		List<DirectedNode> nodes;
		List<DirectedNode> path1 = depthFirstSearch(node1);
		List<DirectedNode> path2 = depthFirstSearch(node2);
		path1.retainAll(path2);
		nodes = removeParentNodes(path1);
		return nodeListToDataArray(nodes);
	}

	public boolean hasCycle() {
		List<Integer> visitedNodes = new ArrayList<Integer>();
		for (int i = 0; i < nodes.size(); i++) {
			DirectedNode root = nodes.get(i);
			if (hasCycleRecursive(visitedNodes, root))
				return true;
		}
		return false;
	}

	private boolean hasCycleRecursive(List<Integer> visitedNodes, DirectedNode node) {
		if (visitedNodes.contains(node.data))
			return true;
		visitedNodes.add(node.data);
		for (int i = 0; i < node.getOutNodeAmount(); i++) {
			DirectedNode iNode = node.outNodes.get(i);
			if (hasCycleRecursive(visitedNodes, iNode))
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

	private List<DirectedNode> depthFirstSearch(int data) {
		List<DirectedNode> visitedNodes = new ArrayList<DirectedNode>();
		DirectedNode targetNode = getNode(data);
		DirectedNode root = getNode(0);
		depthFirstSearchRecursive(targetNode, root, visitedNodes);
		return visitedNodes;
	}

	private void depthFirstSearchRecursive(DirectedNode targetNode, DirectedNode currentNode,
			List<DirectedNode> visitedNodes) {
		if (targetNode == currentNode) {
			visitedNodes.add(targetNode);
			return;
		}
		if (currentNode.getOutNodeAmount() == 0)
			return;
		if (!visitedNodes.contains(currentNode))
			visitedNodes.add(currentNode);
		for (int i = 0; i < currentNode.getOutNodeAmount(); i++) {
			DirectedNode iNode = currentNode.outNodes.get(i);
			depthFirstSearchRecursive(targetNode, iNode, visitedNodes);
			if (containsNode(targetNode, visitedNodes))
				return;
		}
		if (!visitedNodes.contains(targetNode))
			visitedNodes.remove(currentNode);
	}

	private List<DirectedNode> removeParentNodes(List<DirectedNode> path) {
		for (int i = 0; i < path.size(); i++) {
			DirectedNode node = path.get(i);
			boolean hasChild = false;
			for (int j = 0; j < node.getOutNodeAmount(); j++) {
				if (containsNode(node.outNodes.get(j), path)) {
					hasChild = true;
				}
			}
			if (hasChild == true) {
				path.remove(node);
				i--;
			}
		}
		return path;
	}

	private int[] nodeListToDataArray(List<DirectedNode> nodes) {
		int[] lowestCommonAncestor = new int[nodes.size()];
		for (int i = 0; i < lowestCommonAncestor.length; i++) {
			lowestCommonAncestor[i] = nodes.get(i).data;
		}
		return lowestCommonAncestor;
	}

	private boolean containsNode(DirectedNode targetNode, List<DirectedNode> visitedNodes) {
		if(targetNode == null)
			return false;
		int targetData = targetNode.data;
		for (int i = 0; i < visitedNodes.size(); i++) {
			DirectedNode node = visitedNodes.get(i);
			if (node.data == targetData)
				return true;
		}
		return false;
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
