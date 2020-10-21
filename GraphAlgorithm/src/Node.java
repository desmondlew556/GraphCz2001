
public class Node {
	private int NodeNum;
	private Node next;
	public Node(int num) {
		NodeNum = num;
		next = null;
	}
	public void setNextNode(Node nextNode) {
		next = nextNode;
	}
	public int getNodeNum() {
		return NodeNum;
	}
	public Node getNextNode() {
		return next;
	}
}
