
public class Node {
	private int value;
	private Node next;
	public Node(int num) {
		value=num;
		next=null;
	}
	public Node(int num, Node nextNode) {
		value=num;
		next=nextNode;
	}
	public int getValue() {
		return value;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
}
