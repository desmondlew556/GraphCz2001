import java.util.LinkedList;
public class Queue {
	private LinkedList<NetworkNode> ll;
	public Queue() {
		ll=new LinkedList<NetworkNode>();
	}
	public Queue(NetworkNode head) {
		ll=new LinkedList<NetworkNode>();
		ll.add(head);
	}
	public void enqueue(NetworkNode nextNode) {
		ll.addLast(nextNode);
	}
	public NetworkNode dequeue() {
		NetworkNode temp;
		if(ll.size()!=0) {
			temp = ll.getFirst();
			ll.removeFirst();
		}
		else
			temp=null;
		return temp;
	}
	public boolean isEmpty() {
		if(ll.size()==0)
			return true;
		else
			return false;
	}
}
