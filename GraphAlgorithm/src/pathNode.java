
public class pathNode {
	private int nodeNum;
	//previous stores the address of the previous node
	private pathNode previous;
	private pathNode next;
	public pathNode(int nodeNum,pathNode prevNode) {
		this.nodeNum = nodeNum;
		previous = prevNode;
		next = null;
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public pathNode getPrevNode() {
		return previous;
	}
	public void setNextNode(pathNode nextNode) {
		next = nextNode;
	}
	public pathNode getNextNode() {
		return next;
	}
}
