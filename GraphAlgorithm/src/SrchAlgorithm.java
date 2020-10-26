import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
public class SrchAlgorithm {
	//create BFS tree
	private static ArrayList<Node> HospitalPath = new ArrayList<Node>();
	private static ArrayList<Node> HospitalsFound = new ArrayList<Node>();
	
	//Find nearest hospitals from a node
	//parameters include graph, index of start node, and number of nearest hospitals to find.
	public static void searchNearestHospitals(NetworkNode[] network,int startNode,int numHospital, NodeInfo[] BFSInfo) {
		//initialise variables
		if(numHospital<=0) {
			System.out.println("Exiting since you don't want to find any hospitals!");
			return;
		}
		int i=startNode;
		int dist = 0;
		NetworkNode current;
		LinkedList<Node> linkedlist;
		//Search start node and add to list of visited nodes
		current=network[i];
		//Every time a network node is visited, 3 things are done:
		//1. set distance in BFSInfo to indicate that node is visited
		BFSInfo[i].setDist(dist);
		//2. Set the predecessor of BFS info to -1 to indicate it is the first node.
		BFSInfo[i].setPredecessor(-1);
		//3. check if first node is a hospital
		if((current.isHospital()==true)) {
			HospitalsFound.add(new Node(current.getValue()));
			//decrement number of hospitals to find by 1
			numHospital--;
			//if all hospitals required are found, exit.
			if(numHospital==0)
				return;
		}
		//increment distance
		dist++;
		//store nodes to check in a queue
		Queue NodesQueue = new Queue(current);
		//if queue is not empty, continue checking if that node is connected to a hospital
		while(!(NodesQueue.isEmpty())) {
			//get first node in queue
			current=NodesQueue.dequeue();
			int currentNodeIndex=current.getValue();
			//get linkedlist to that node, in order  to find edges
			linkedlist=current.getEdges();
			//initialise variables 
			//incidentNode are the nodes incident to the current node. Get first incident node.
			NetworkNode incidentNode;
			//initialise loop control
			int j=0;
			//Check through all elements in linked list
			while(j<linkedlist.size()) {
				int nodeIndex = linkedlist.get(j).getValue();
				incidentNode = network[nodeIndex];
				//Visit the node only if it is not visited. That is, dist=-1.
				if(BFSInfo[nodeIndex].getDist()==-1) {
					//1. set distance to indicate that node is visited
					BFSInfo[nodeIndex].setDist(dist);
					//2. Set the predecessor node
					BFSInfo[nodeIndex].setPredecessor(currentNodeIndex);
					//3. check if first node is a hospital
					if((incidentNode.isHospital()==true)) {
						HospitalsFound.add(new Node(incidentNode.getValue()));
						//decrement number of hospitals to find by 1
						numHospital--;
						//if all hospitals required are found, exit.
						if(numHospital==0)
							return;
					}
					//add the checked node to the NodesQueue
					NodesQueue.enqueue(incidentNode);
				}
				j++;
			}
			//Search nodes at next depth
			dist++;
		}	
		System.out.printf("Cannot find last %d hospitals\n",numHospital);
		return;
	}
	public static void printPaths(NetworkNode[] network, NodeInfo[] BFSInfo) {
        //Creates visited nodes and stores them in HospitalPath
        //For traversing HospitalPath
        int j=0;
        int i;
		for(i=0;i<HospitalsFound.size();i++) {
			int dist=0;
            //BFSNode is the nodes in the HospitalPath
            //cur traverses the graph to find the predecessors
            Node BFSNode = HospitalsFound.get(i);
            NodeInfo cur = BFSInfo[BFSNode.getValue()];
			while(cur.getPredecessor()!=-1) {
				//predecessor value gives index of previous node, which is the node value
				HospitalPath.add(new Node(cur.getPredecessor(),BFSNode));
                cur=BFSInfo[cur.getPredecessor()];
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
	public static void clearSearch() {
		HospitalPath.clear();
		HospitalsFound.clear();
	}
	public static void resetBFSInfo(NodeInfo[] BFSInfo, int size) {
		for(int i=0;i<size;i++) {
			BFSInfo[i].setDist(-1);
			BFSInfo[i].setPredecessor(-1);
		}
	}
	public static void searchMethod1(int startNode,int numHospitals) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter file name for graph(eg roadNet-PA.txt):");
		String filename=userInput.next();
		File file = new File(filename);
		try {
			Preprocessing.testFile(file);
		}
		catch(FileNotFoundException e) {
			System.out.println("File for graph information cannot be read.");
			return;
		}
		
		System.out.println("Enter file name storing hospitals nodes (eg hospital.txt):");
		String fname =userInput.next();
		File f2 = new File(fname);
		try {
			Preprocessing.testFile(f2);
		}
		catch(FileNotFoundException e) {
			System.out.println("File for hospital information cannot be read.");
			return;
		}
		try {
			System.out.println("Preprocessing data");
			int size = Preprocessing.getSizeOfGraph(file);
			System.out.println("Size obtained.");
			NetworkNode[] graph = new NetworkNode[size];
			graph = Preprocessing.generateGraph(file,size);
			System.out.println("Graph generated.");
			
			Preprocessing.setHospitalNodes(f2,graph);
			int i;
			NodeInfo[] BFSInfo = new NodeInfo[size];
			for(i=0;i<size;i++) {
				BFSInfo[i]=new NodeInfo();
			}
		
			System.out.printf("Node %d\n",startNode);
			searchNearestHospitals(graph,startNode,numHospitals,BFSInfo);
			printPaths(graph,BFSInfo);
			clearSearch();
			System.out.println();
			resetBFSInfo(BFSInfo,size);
		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be read.");
		}
	}
	public static void searchMethod2(int numHospitals) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter file name for graph(eg roadNet-PA.txt):");
		String filename=userInput.next();
		File file = new File(filename);
		try {
			Preprocessing.testFile(file);
		}
		catch(FileNotFoundException e) {
			System.out.println("File for graph information cannot be read.");
			return;
		}
		
		System.out.println("Enter file name storing hospitals nodes (eg hospital.txt):");
		String fname =userInput.next();
		File f2 = new File(fname);
		try {
			Preprocessing.testFile(f2);
		}
		catch(FileNotFoundException e) {
			System.out.println("File for hospital information cannot be read.");
			return;
		}
		try {
			int size = Preprocessing.getSizeOfGraph(file);
			NetworkNode[] graph = new NetworkNode[size];
			graph = Preprocessing.generateGraph(file,size);
			
			Preprocessing.setHospitalNodes(f2,graph);
			int i;
			NodeInfo[] BFSInfo = new NodeInfo[size];
			for(i=0;i<size;i++) {
				BFSInfo[i]=new NodeInfo();
			}
			int j;
			for(i=0;i<size;i++) {
				System.out.printf("Node %d\n",i);
				searchNearestHospitals(graph,i,numHospitals,BFSInfo);
				printPaths(graph,BFSInfo);
				clearSearch();
				System.out.println();
				resetBFSInfo(BFSInfo,size);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be read.");
		}
	}
	
	public static void methodA() {
		String filename;
		Scanner userInput = new Scanner(System.in);
		System.out.println("How many nearest hospitals do you want to find?");
		int numHospitals;
		//Choose number of nearest hospitals to find
		while(true) {
			while(!userInput.hasNextInt()) {
				userInput.next();
				System.out.println("Please enter a number:");
			}
			numHospitals=userInput.nextInt();
			if(numHospitals>0)
				break;
			System.out.println("Enter a positive whole number:");
		}
		//Choose to find from a node or from all nodes
		char input_method;
    	 System.out.println("Choose one of the following:");
    	 System.out.println("A. Find nearest hospitals from only 1 node");
    	 System.out.println("B. Find nearest hospitals from all node");
    	 input_method = userInput.next().charAt(0);
    	 while((input_method!='A')&&(input_method!='a')&&(input_method!='B')&&(input_method!='b')) {
    		 System.out.println("Choose either option A or B");
    		 input_method = userInput.next().charAt(0);
    	 }
    	 if((input_method=='a')||(input_method=='A')) {
    		 //get which node to find hospitals from
    		 int startNode;
    		 System.out.println("Please choose starting node:");
    		 while(true) {
    				while(!userInput.hasNextInt()) {
    					userInput.next();
    					System.out.println("Please enter a number:");
    				}
    				startNode=userInput.nextInt();
    				if(startNode>=0)
    					break;
    				System.out.println("Enter a positive whole number:");
    			}
    		 searchMethod1(startNode,numHospitals); //read files and start search
    	 }
    	 else {
    		 //read input sequences and run algorithm
	    	 searchMethod2(numHospitals);
    	 }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test on a simple graph
		/*NetworkNode[] network = new NetworkNode[6];
		network[0]=new NetworkNode(0);
		network[1]=new NetworkNode(1);
		network[2]=new NetworkNode(2);
		network[3]=new NetworkNode(3);
		network[4]=new NetworkNode(4);
		network[5]=new NetworkNode(5);

		network[0].addEdge(new Node(1));
		network[0].addEdge(new Node(2));
		network[1].addEdge(new Node(3));
		network[1].addEdge(new Node(4));
		network[1].addEdge(new Node(5));
		
		network[1].addEdge(new Node(0));
		network[2].addEdge(new Node(0));
		network[3].addEdge(new Node(1));
		network[4].addEdge(new Node(1));
		network[5].addEdge(new Node(1));
		
		network[1].setHospital();
		network[2].setHospital();
		network[5].setHospital();
		
		int i;
		int size=6;
		NodeInfo[] BFSInfo = new NodeInfo[size];
		for(i=0;i<size;i++) {
			BFSInfo[i]=new NodeInfo();
		}
		for(i=0;i<size;i++) {
			System.out.printf("Node %d\n",i);
			searchNearestHospitals(network,i,3,BFSInfo);
			printPaths(network,BFSInfo);
			clearSearch();
			System.out.println();
			resetBFSInfo(BFSInfo,size);
		}*/
		char choice;
		 char input_method;
		 int i;
		 Scanner userInput = new Scanner(System.in);
		 
	     do {
	    	 System.out.println("Choose one of the following:");
	    	 System.out.println("A. Read text from file");
	    	 System.out.println("B. Read text from input");
	    	 input_method = userInput.next().charAt(0);
	    	 while((input_method!='A')&&(input_method!='a')&&(input_method!='B')&&(input_method!='b')) {
	    		 System.out.println("Choose either option A or B");
	    		 input_method = userInput.next().charAt(0);
	    	 }
	    	 if((input_method=='a')||(input_method=='A')) {
	    		 methodA(); //read file and carry start algorithm
	    	 }
	    	 else {
	    		 //read input sequences and run algorithm
		    	 System.out.println("Placeholder for random graph generator");
	    	 }
		     System.out.println("Press any key to search another time. Otherwise, enter N");
		     choice = userInput.next().charAt(0);
	     }while ((choice!='N')&&(choice!='n'));
	     
	

	}
}