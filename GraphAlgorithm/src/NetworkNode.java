import java.util.ArrayList;
import java.util.LinkedList;
public class NetworkNode {
	//value of the node. If node is invalid, value=-1
	private int value;
	//stores address of linked list.
	private LinkedList<Node> ll=new LinkedList<Node>();
	//if node is a hospital, then hospital=true
	private boolean hospital;
	public NetworkNode() {
		value = -1;
		ll = null;
		hospital=false;
	}
	public NetworkNode(int val) {
		value = val;
		ll = new LinkedList<Node>();
		hospital=false;
	}
	public void setHospital() {
		hospital = true;
	}
	public void addEdge(Node nextNode) {
		if(ll.size()==0) {
			ll.addFirst(nextNode);
		}
		else {
			//Update the last node in linked list before adding new node
			ll.getLast().setNext(nextNode);
			ll.addLast(nextNode);
		}
	}
	public LinkedList<Node> getEdges() {
		return ll;
	}
	public int getValue() {
		return value;
	}
	public boolean isHospital() {
		return hospital;
	}
	//checks if the node is visited
}