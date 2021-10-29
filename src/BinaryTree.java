import java.util.ArrayList;
import java.util.List;

public class BinaryTree
{
    Node root;
    private List<Integer> path1 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();
 
    public int findLCA(int n1, int n2) {
        path1.clear();
        path2.clear();
        return findLCAInternal(root, n1, n2);
    }
    
    private int findLCAInternal(Node root, int n1, int n2) {
        if (findPath(root, n1, path1) == false || findPath(root, n2, path2) == false)
            return -1;
        int i = 0;
        while(i < path1.size() && i < path2.size()) {
            if (!path1.get(i).equals(path2.get(i)))
                break;
            i++;
        }
        return path1.get(i-1);
    }
     
    private boolean findPath(Node root, int n, List<Integer> path)
    {
        if (root == null)
            return false;
        path.add(root.data);
        if (root.data == n)
            return true;
        if (root.left != null && findPath(root.left, n, path))
            return true;
        if (root.right != null && findPath(root.right, n, path))
            return true;
        path.remove(path.size()-1);
        return false;
    }
}