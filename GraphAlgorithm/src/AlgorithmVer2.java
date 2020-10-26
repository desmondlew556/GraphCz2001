import java.util.ArrayList;
import java.util.LinkedList;
public class AlgorithmVer2 {
	//create BFS tree
	private ArrayList<Node> HospitalPath = new ArrayList<Node>();
	private ArrayList<Node> HospitalsFound = new ArrayList<Node>();
	
	//Find nearest hospitals from a node
	//parameters include graph, index of start node, and number of nearest hospitals to find.
	public NetworkNodeVer2[] searchNearestHospitals(NetworkNodeVer2[] network,int startNode,int numHospital) {
		//initialise variables
		int i=startNode;
		int dist = 0;
		NetworkNodeVer2 current;
		LinkedList<Node> linkedlist;
		//Search start node and add to list of visited nodes
		current=network[i];
		//Every time a network node is visited, 3 things are done:
		//1. set distance to indicate that node is visited
		current.setDist(dist);
		//2. Set the predecessor node
		current.setPredecessor(null);
		//3. check if first node is a hospital
		if((current.isHospital()==true)) {
			HospitalsFound.add(new Node(current.getValue()));
			//decrement number of hospitals to find by 1
			numHospital--;
			//if all hospitals required are found, exit.
			if(numHospital==0)
				return network;
		}
		//increment distance
		dist++;
		//store nodes to check in a queue
		Queue NodesQueue = new Queue(current);
		//if queue is not empty, continue checking if that node is connected to a hospital
		while(!(NodesQueue.isEmpty())) {
			//get first node in queue
			current=NodesQueue.dequeue();
			System.out.println(current.getValue());
			//get linkedlist to that node, in order  to find edges
			linkedlist=current.getEdges();
			//initialise variables 
			//incidentNode are the nodes incident to the current node. Get first incident node.
			NetworkNodeVer2 incidentNode;
			//initialise loop control
			int j=0;
			//Check through all elements in linked list
			while(j<linkedlist.size()) {
				incidentNode = network[linkedlist.get(j).getValue()];
				System.out.println("edge"+incidentNode.getValue());
				//Visit the node only if it is not visited. That is, dist=-1.
				if(incidentNode.getDist()==-1) {
					//1. set distance to indicate that node is visited
					incidentNode.setDist(dist);
					//2. Set the predecessor node
					incidentNode.setPredecessor(current);
					//3. check if first node is a hospital
					if((incidentNode.isHospital()==true)) {
						HospitalsFound.add(new Node(incidentNode.getValue()));
						//decrement number of hospitals to find by 1
						numHospital--;
						//if all hospitals required are found, exit.
						if(numHospital==0)
							return network;
					}
					//add the checked node to the NodesQueue
					NodesQueue.enqueue(incidentNode);
					j++;
				}
			}
			//Search nodes at next depth
			dist++;
		}	
		System.out.printf("Cannot find last %d hospitals\n",numHospital);
		return network;
	}
	public void printPaths(NetworkNodeVer2[] network) {
        //Creates visited nodes and stores them in HospitalPath
        //For traversing HospitalPath
        int j=0;
        int i;
		for(i=0;i<HospitalsFound.size();i++) {
			int dist=0;
            //BFSNode is the nodes in the HospitalPath
            //cur traverses the graph to find the predecessors
            Node BFSNode = HospitalsFound.get(i);
            NetworkNodeVer2 cur = network[BFSNode.getValue()];
			while(cur.getPredecessor()!=null) {
                cur=cur.getPredecessor();
				HospitalPath.add(new Node(cur.getValue(),BFSNode));
				BFSNode = HospitalPath.get(j);
                j++;
				dist++;
			}
			System.out.printf("Path %d with distance %d:\n",i+1,dist);
			while(BFSNode!=null) {
				System.out.printf("%d->",BFSNode.getValue());
				BFSNode=BFSNode.getNext();
			}
			System.out.print("end");
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetworkNodeVer2[] network = new NetworkNodeVer2[6];
		network[0]=new NetworkNodeVer2(0);
		network[1]=new NetworkNodeVer2(1);
		network[2]=new NetworkNodeVer2(2);
		network[3]=new NetworkNodeVer2(3);
		network[4]=new NetworkNodeVer2(4);
		network[5]=new NetworkNodeVer2(5);

		network[0].addEdge(new Node(1));
		network[0].addEdge(new Node(2));
		network[1].addEdge(new Node(3));
		network[1].addEdge(new Node(4));
		network[1].addEdge(new Node(5));
		
		network[1].SetHospital();
		network[2].SetHospital();
		network[5].SetHospital();
		AlgorithmVer2 search=new AlgorithmVer2();
		search.searchNearestHospitals(network,0,5);
		search.printPaths(network);
	}
}
