
public class NetworkNode {
	//value of the node
	private int nodeNum;
	//stores address of node which is incident to it.
	private Node next = null;
	//stores address of last node
	private Node current = null;
	//data on nearest hospital and distance to hospital
	//nearestHos[0] is the node of the nearest hospital, nearestHos[1] is the distance to the nearest hospital
	private int[] nearestHos=new int[2];
	//if node is a hospital, then hospital=true
	private boolean hospital=false;
	
	public NetworkNode(int nodeNum) {
		this.nodeNum = nodeNum;
		next = null;
		current = null;
		nearestHos[0]=-1;
		nearestHos[1]=-1;
		hospital=false;
	}
	public void SetHospital() {
		hospital = true;
		nearestHos[0]=nodeNum;
		nearestHos[1]=0;
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
		nearestHos[0] = nodeNum;
		nearestHos[1] = dist;
	}
	public int[] getNearestHospital() {
		return nearestHos;
	}
}
