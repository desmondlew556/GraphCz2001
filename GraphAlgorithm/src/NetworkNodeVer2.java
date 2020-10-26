import java.util.ArrayList;
import java.util.LinkedList;
public class NetworkNodeVer2 {
	//value of the node. If node is invalid, value=-1
	private int value;
	//stores address of linked list.
	private LinkedList<Node> ll=new LinkedList<Node>();
	//if node is a hospital, then hospital=true
	private boolean hospital;
	//dist stores the distance from hospital. And is the depth of the hospital node
	private int dist;
	private NetworkNodeVer2 predecessor;
	public NetworkNodeVer2() {
		value = -1;
		ll = null;
		hospital=false;
		dist=-1;
		predecessor = null;
	}
	public NetworkNodeVer2(int val) {
		value = val;
		ll = new LinkedList<Node>();
		hospital=false;
		dist=-1;
		predecessor = null;
	}
	
	public void setDist(int distance) {
		dist = distance;
	}
	public void SetHospital() {
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
	public void setPredecessor(NetworkNodeVer2 prev) {
		predecessor=prev;
	}
	public LinkedList<Node> getEdges() {
		return ll;
	}
	public NetworkNodeVer2 getPredecessor() {
		return predecessor;
	}
	public int getValue() {
		return value;
	}
	public int getDist() {
		return dist;
	}
	public boolean isHospital() {
		return hospital;
	}
	//checks if the node is visited
	public boolean isVisited() {
		if(dist==-1)
			return false;
		else
			return true;
	}
}
