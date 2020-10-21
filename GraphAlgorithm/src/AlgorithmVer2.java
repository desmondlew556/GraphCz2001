import java.util.ArrayList;

public class AlgorithmVer2 {
	private static ArrayList<Node> pathToHospital = new ArrayList<Node>();
	
	public static int[] generateHospitalArray() {
		int[] hospitals = new int[H];
		return hospitals;
	}
	public static NetworkNodeVer2[] generateNetwork(int[] hospital_array) {
		//read file input and created a linked list array
		
		int i;
		//linked list array of M linked lists, one for each node
		NetworkNodeVer2[] network = new NetworkNodeVer2[M];
		//index to use for getting node value
		int nodeValue;
		//Preprocessing
		//Read file, initialise node and add edge information
		Readfile {
			if(network[nodeValue]==null)
				network[nodeValue] = new NetworkNodeVer2(nodeValue);
			//if a node is incident to another node, store the former node value in linked list of latter node.
			Node node = new Node(incidentNodeValue);
			network[nodeValue].addNode(node);
		}
		
		
		//get all hospital nodes
		int[] hospitals = new int[H];
		hospitals = hospital_array;
		//update if a node is a hospital in the networks node
		for(i=0;i<hospitals.length;i++) {
			//assume hospital array store node location
			network[hospitals[i]].SetHospital();
		}
		return network;
	}
	public static void searchNearestHospitals(NetworkNodeVer2[] network,int numHospitals) {
		int i;
		//for each node in network, find nearest hospital
		for(i=0;i<network.length;i++) {
			//Set the number of hospitals to find
			int numHos = numHospitals;
			int dist = 1;
			if(network[i].isHospital()==true) {
				pathToHospital.add(new Node(network[i].getNodeNum()));
				numHos--;
			}
			else {
				//Create a linked list for path to hospital and store the starting node as the first node
				pathNode parentNode = new pathNode(network[i].getNodeNum(),null);
				//store nodes to check in a queue
				Queue NodesQueue = new Queue(parentNode);
				//if queue is not empty, continue checking if that node is connected to a hospital
				while(NodesQueue.headNotNull()) {
					//get the first node in queue
					pathNode networknode = NodesQueue.getNode();
					//get node number in order to get linked list of incident nodes
					int nodeIndex = networknode.getNodeNum();
					//Current iterates through the incident nodes of the parent node. Check the first node in the linked list first.
					Node current = network[nodeIndex].getNextNode();
					while(current!=null) {
						//create the child node and link to the previous node to store path information
						pathNode childNode = new pathNode(current.getNodeNum(),networknode);
						//check if the node is a hospital
						if(network[current.getNodeNum()].isHospital()==true) {
							//if hospital is found, add the path into the list of paths
							addPath(childNode);
							//add to node in linked list
							network[i].setNearestHospital(current.getNodeNum(), dist);
							numHos--;
							//stop searching when desired number of nearest hospitals found
							if(numHos==0)
								break;
						}
						//add the checked node to the NodesQueue
						NodesQueue.addNode(childNode);
						//get the next incident node
						current = current.getNextNode();
					}
					//If desired number of nearest hospitals are found, move onto the next node in network.
					if(numHos==0)
						break;
					//search nodes that are 1 edge further
					dist++;
					//parent node
				}

				
			}
				
		}
	}
	public static void addPath(pathNode lastNode) {
		pathNode current = lastNode;
		//get the first node in the path
		while(current.getPrevNode()!=null) {
			current = current.getPrevNode();
		}
		
		//create a new linked list and add to the list of pathToHospitals
		Node firstNode = new Node(current.getNodeNum());
		//temp node to add nodes to new linked list
		Node curNode = firstNode;
		while(current.getNextNode()!=null) {
			current=current.getNextNode();
			Node nextNode = new Node(current.getNodeNum());
			curNode.setNextNode(nextNode);
			curNode = nextNode;
		}
		pathToHospital.add(firstNode);
	}
	public static void printPaths() {
		int i;
		for(i=0;i<pathToHospital.size();i++) {
			Node cur = pathToHospital.get(i);
			while(cur!=null) {
				System.out.printf("%d->",cur.getNodeNum());
			}
			System.out.print("end\n");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetworkNodeVer2[] network = new NetworkNodeVer2[6];
		network[0] = new NetworkNodeVer2(0);
		network[1] = new NetworkNodeVer2(1);
		network[2] = new NetworkNodeVer2(2);
		network[3] = new NetworkNodeVer2(3);
		network[4] = new NetworkNodeVer2(4);
		network[5] = new NetworkNodeVer2(5);

		network[0].addNode(new Node(1));
		network[0].addNode(new Node(2));
		network[1].addNode(new Node(3));
		network[1].addNode(new Node(4));
		network[1].addNode(new Node(5));
		
		network[1].SetHospital();
		network[2].SetHospital();
		network[5].SetHospital();
		
		searchNearestHospitals(network,2);
		printPaths();
	}
}
