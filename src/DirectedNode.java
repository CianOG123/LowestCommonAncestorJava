import java.util.ArrayList;
import java.util.List;

public class DirectedNode {
	
	int data;
	List<DirectedNode> outNodes = new ArrayList<DirectedNode>();
	
	DirectedNode(int data){
		this.data = data;
	}
	
	
	public void addEdgeTo(DirectedNode node) {
		outNodes.add(node);
	}
	
	public boolean edgeExistsTo(DirectedNode node) {
		if(outNodes.contains(node))
			return true;
		return false;
	}
	
	public int getOutNodeAmount() {
		return outNodes.size();
	}

}
