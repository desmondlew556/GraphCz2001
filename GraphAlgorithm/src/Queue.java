
public class Queue {
	private pathNode head;
	private pathNode tail;
	public Queue() {
		head = null;
		tail = null;
	}
	public Queue(pathNode head) {
		this.head = head;
		tail = head;
	}
	public void addNode(pathNode nextNode) {
		if(tail==null) {
			head = nextNode;
			tail = nextNode;
		}
		else {
			tail.setNextNode(nextNode);
			tail = nextNode;
		}
	}
	public pathNode getNode() {
		pathNode temp = head;
		if(head!=null) {
			head = head.getNextNode();
		}
		return temp;
	}
	public boolean headNotNull() {
		if(head==null)
			return false;
		else
			return true;
	}
}
