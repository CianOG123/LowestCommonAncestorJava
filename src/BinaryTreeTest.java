import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryTreeTest {

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
}
