import java.util.ArrayList;
public class NetworkNodeVer2 {
	//value of the node
	private int nodeNum;
	//stores address of node which is incident to it.
	private Node next = null;
	//stores address of last node
	private Node current = null;
	//data on nearest hospital and distance to hospital
	//nearestHos[0] is the node of the nearest hospital, nearestHos[1] is the distance to the nearest hospital
	private ArrayList<NodeAndDist> nearestHospitals = new ArrayList<NodeAndDist>();
	//if node is a hospital, then hospital=true
	private boolean hospital=false;
	
	public NetworkNodeVer2(int nodeNum) {
		this.nodeNum = nodeNum;
		next = null;
		current = null;
		hospital=false;
	}
	public void SetHospital() {
		hospital = true;
		nearestHospitals.add(new NodeAndDist(nodeNum,0));
	}
	public boolean isHospital() {
		return hospital;
	}
	public void addNode(Node nextNode) {
		if(next==null) {
			next = nextNode;
			current = nextNode;
		}
		else {
			//Update the last node in linked list before adding new node
			current.setNextNode(nextNode);
			current = nextNode;
		}
		
	}
	public Node getNextNode() {
		return next;
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public void setNearestHospital(int nodeNum, int dist) {
		nearestHospitals.add(new NodeAndDist(nodeNum,dist));
	}
	public ArrayList<NodeAndDist> getNearestHospital() {
		return nearestHospitals;
	}
}
