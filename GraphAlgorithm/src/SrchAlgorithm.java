//reference.https://www.khanacademy.org/computing/computer-science/algorithms/breadth-first-search/pc/challenge-implement-breadth-first-search
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
public class SrchAlgorithm {
	//create BFS tree
	private static ArrayList<Node> HospitalPath = new ArrayList<Node>();
	private static ArrayList<Node> HospitalsFound = new ArrayList<Node>();
	private static ArrayList<Node> startNodes = new ArrayList<Node>();
	private static int nodeIndex;
	//Variables used for time complexity analysis
	protected static int numOfNodesQueued;
	protected static int numOfEdgesTraversed;
	
	//Find nearest hospitals from a node
	//parameters include graph, index of start node, and number of nearest hospitals to find.
	public static void searchNearestHospitals(NetworkNode[] network, NodeInfo[] BFSInfo,int startNode,int numHospital, int numHospitalsPresent) {
		if(numHospital<=0) {
			System.out.println("Exiting since you don't want to find any hospitals!");
			return;
		}
		
		//initialise variables
		int numOfHospitalsNotVisited=numHospitalsPresent;
		//exit search if there are no hospitals to visit.
		if(numOfHospitalsNotVisited==0) {
			if(numHospital!=0)
				System.out.printf("Couldn't find last %d hospitals\n",numHospital);
			return;
		}
		int i=startNode;
		//node does not exists if value is -1
		if(network[i].getValue()==-1)
			return;
		int dist =0;
		NetworkNode current;
		LinkedList<Node> linkedlist;
		//Search start node and add to list of visited nodes
		current=network[i];
		//Every time a network node is visited, 3 things are done:
		//1. set distance in BFSInfo to indicate that node is visited
		BFSInfo[i].setDist(dist);
		//2. Set the predecessor of BFS info to -1 to indicate it is the first node.
		//Start node has no predecessor
		//3. check if first node is a hospital
		if((current.isHospital()==true)) {
			//if hospital not in list of hospitals found, add to the list
			HospitalsFound.add(new Node(current.getValue()));
			//decrement number of hospitals to find by 1
			numHospital--;
			numOfHospitalsNotVisited--;
			//if all hospitals required are found or if no more hospitals to visit, exit.
			if((numHospital==0)||(numOfHospitalsNotVisited==0)) {
				if(numHospital!=0)
					System.out.printf("Couldn't find last %d hospitals\n",numHospital);
				return;
			}
		}
		
		
		//store nodes to check in a queue
		Queue NodesQueue = new Queue(current);
		//Debugging
		//System.out.println(numOfNodesQueued);
		//System.out.println(numOfEdgesTraversed);
		numOfNodesQueued=1;
		numOfEdgesTraversed=0;
		
		//if queue is not empty, continue checking if that node is connected to a hospital
		while(!(NodesQueue.isEmpty())) {
			//get first node in queue
			current=NodesQueue.dequeue();
			//set distance 1 more than distance to current node
			dist = BFSInfo[current.getValue()].getDist()+1;
			int currentNodeIndex=current.getValue();
			//get linkedlist to that node, in order  to find edges
			linkedlist=current.getEdges();
			//initialise variables 
			//incidentNode are the nodes incident to the current node. Get first incident node.
			NetworkNode incidentNode;
			//initialise loop control
			int j=0;
			//Debug
			//System.out.println("Dequeued node"+currentNodeIndex);
			//Check through all elements in linked list
			while(j<linkedlist.size()) {
				int nodeIndex = linkedlist.get(j).getValue();
				incidentNode = network[nodeIndex];
				//Debug
				//System.out.println("Checking node"+incidentNode.getValue());
				//Visit the node only if it is not visited yet or visited in a similarly short path.
				//Also ensures search will not traverse the path already searched because distance will be larger than previous distance set.
				if(dist<BFSInfo[nodeIndex].getDist()) {
					//Debug
					//System.out.println("setting distance"+BFSInfo[nodeIndex].getDist()+" to "+dist);
					//System.out.println();
					
					//1. set distance to indicate that node is visited
					BFSInfo[nodeIndex].setDist(dist);
					//Add number of edges traversed
					numOfEdgesTraversed++;
					//2. Set the predecessor node
					BFSInfo[nodeIndex].addPredecessor(currentNodeIndex);
					//3. Add to list of hospitals.
					if(incidentNode.isHospital()) {
						HospitalsFound.add(new Node(nodeIndex));
						//decrement number of hospitals to find by 1
						numHospital--;
						numOfHospitalsNotVisited--;
						//if all hospitals required are found or if no more hospitals to visit, exit.
						if((numHospital==0)||(numOfHospitalsNotVisited==0)) {
							if(numHospital!=0)
								System.out.printf("Couldn't find last %d hospitals\n",numHospital);
							return;
						}
					}
						//add the checked node to the NodesQueue
						NodesQueue.enqueue(incidentNode);
						numOfNodesQueued++;
					}
				//if the distance is not MAX_INT, it means that the node is already visited. If two paths or more paths arrive at a node with the same path length, 
				//then a predecessor should be added to the node again,but the node should not be queued again.  
					else if(dist==BFSInfo[nodeIndex].getDist()) {
						//1. set distance to indicate that node is visited
						//2. Set the predecessor node
						BFSInfo[nodeIndex].addPredecessor(currentNodeIndex);
						//Add number of edges traversed
						numOfEdgesTraversed++;
				}
				j++;
			}
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
            Node temp = BFSNode;
            nodeIndex=0;
            getPath(BFSInfo,BFSNode);
    		for(j=0;j<startNodes.size();j++) {
    			dist=-1;
    			BFSNode = startNodes.get(j);
    			while(BFSNode!=null) {
    				System.out.printf("%d->",BFSNode.getValue());
    				BFSNode=BFSNode.getNext();
    				dist++;
    			}
    			System.out.print("end");
    			System.out.println();
    			System.out.printf("To hospital %d with distance %d.\n", temp.getValue(),dist);
    			
    		}
    		startNodes.clear();
    		HospitalPath.clear();
		}
	}
	public static void getPath(NodeInfo[] BFSInfo,Node node) {
		//if the node has no predecessor, it is the start node.
		ArrayList<Integer> prev_list = BFSInfo[node.getValue()].getPredecessor();
		if(prev_list.size()==0) {
			startNodes.add(node);
		}
		else {
			int i;
			for(i=0;i<prev_list.size();i++) {
				HospitalPath.add(new Node(prev_list.get(i),node));
				nodeIndex++;
				getPath(BFSInfo,HospitalPath.get(nodeIndex-1));
			}
		}
	}
	//demo to print out paths
	public static void Demo_printDist_Hospital(NodeInfo[] BFSInfo) {
		int i;
		for(i=0;i<HospitalsFound.size();i++)
			System.out.printf("To hospital %d, %d units away\n",HospitalsFound.get(i).getValue(),BFSInfo[HospitalsFound.get(i).getValue()].getDist());
	}
	//return output to write to file
	public static String printDist_Hospital(NodeInfo[] BFSInfo) {
		int i;
		String toWrite="";
		for(i=0;i<HospitalsFound.size();i++) {
			System.out.printf("To hospital %d, %d units away\n",HospitalsFound.get(i).getValue(),BFSInfo[HospitalsFound.get(i).getValue()].getDist());
			toWrite+="To hospital "+String.valueOf(HospitalsFound.get(i).getValue())+", "+String.valueOf(BFSInfo[HospitalsFound.get(i).getValue()].getDist())
			+" units away\n";
		}
		
		
		return toWrite ; 

	}
	public static void clearSearch() {
		HospitalPath.clear();
		HospitalsFound.clear();
	}
	public static void resetBFSInfo(NodeInfo[] BFSInfo, int size) {
		for(int i=0;i<size;i++) {
			BFSInfo[i].reset();
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
			//Copy output to file
			System.out.println("Please input a file name for the output");
	    	Scanner sc = new Scanner(System.in);
	    	String inputName = sc.nextLine();
	    	
	        File myObj = new File(inputName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File already exists.");
	          return;
	        }
	        FileWriter myWriter = new FileWriter(inputName);
			System.out.println("Preprocessing data");
			int size = Preprocessing.getSizeOfGraph(file);
			System.out.println("Size obtained.");
			NetworkNode[] network = new NetworkNode[size];
			network = Preprocessing.generateGraph(file,size);
			System.out.println("Graph generated.");
			int numHospitalNodes;
			numHospitalNodes=Preprocessing.setHospitalNodes(f2,network);
			if(numHospitalNodes>size) {
				System.out.println("Number of hospitals cannot be larger than number of nodes.");
				return;
			}
			int i;
			NodeInfo[] BFSInfo = new NodeInfo[size];
			for(i=0;i<size;i++) {
				BFSInfo[i]=new NodeInfo();
			}
		
			
	        
			System.out.printf("Node %d\n",startNode);
			myWriter.write("Node "+String.valueOf(startNode)+"\n");
			searchNearestHospitals(network, BFSInfo, startNode,numHospitals,numHospitalNodes);
			String toWrite="";
			toWrite = printDist_Hospital(BFSInfo);
	        myWriter.write(toWrite);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	        
	        //For printing on console
			//System.out.printf("Node %d\n",startNode);
			//searchNearestHospitals(network, BFSInfo, startNode,numHospitals,numHospitalNodes);
			//Demo_printDist_Hospital(BFSInfo);
			//printPaths(network, BFSInfo);
	        
	        //clear datastructures for search
			clearSearch();
			System.out.println();
			resetBFSInfo(BFSInfo,size);
		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be read.");
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
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
			//get file name to create a new file for outputs.
			String toWrite="";
	    	System.out.println("Please input a file name for the output");
	    	Scanner sc = new Scanner(System.in);
	    	String inputName = sc.nextLine();
	        File myObj = new File(inputName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File already exists.");
	          return;
	        }
	        
	        FileWriter myWriter = new FileWriter(inputName);
			int size = Preprocessing.getSizeOfGraph(file);
			NetworkNode[] network = new NetworkNode[size];
			network = Preprocessing.generateGraph(file,size);
			int numHospitalNodes;
			numHospitalNodes=Preprocessing.setHospitalNodes(f2,network);
			if(numHospitalNodes>size) {
				System.out.println("Number of hospitals cannot be larger than number of nodes.");
				return;
			}
			int i;
			NodeInfo[] BFSInfo = new NodeInfo[size];
			for(i=0;i<size;i++) {
				BFSInfo[i]=new NodeInfo();
			}
			
			
			//search for each node and save output in text file.
			int j;
			for(i=0;i<size;i++) {
				System.out.printf("Node %d\n",i);
				myWriter.write("Node "+String.valueOf(i)+"\n");
				searchNearestHospitals(network, BFSInfo,i,numHospitals,numHospitalNodes);
				toWrite = printDist_Hospital(BFSInfo);
				myWriter.write(toWrite);
				//printPaths(network, BFSInfo);
				
				clearSearch();
				System.out.println();
				resetBFSInfo(BFSInfo,size);
			}
			
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be read.");
		}
		 catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
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
	
	public static void methodB() {
		String filename;
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Generating random graph...");
		System.out.println("How many nodes should the graph have?");
		int numOfNodes;
		//Choose number of nearest hospitals to find
		while(true) {
			while(!userInput.hasNextInt()) {
				userInput.next();
				System.out.println("Please enter a number:");
			}
			numOfNodes=userInput.nextInt();
			if(numOfNodes>0)
				break;
			System.out.println("Enter a positive whole number:");
		}
		
		System.out.println("How many hospitals should there be?");
		int numberOfHospitals;
		//Choose number of nearest hospitals to find
		while(true) {
			while(!userInput.hasNextInt()) {
				userInput.next();
				System.out.println("Please enter a number:");
			}
			numberOfHospitals=userInput.nextInt();
			if(numberOfHospitals>0&&numberOfHospitals<=numOfNodes)
				break;
			System.out.printf("Enter a positive whole number between 0 and %d:\n",numOfNodes);
		}
		
		System.out.println("What fraction of edges should the graph contain (a decimal between 0 and 1 inclusive. Eg 0.3)?");
		double percentEdges;
		//Choose number of nearest hospitals to find
		while(true) {
			while(!userInput.hasNextDouble()) {
				userInput.next();
				System.out.println("Please enter a decimal:");
			}
			percentEdges=userInput.nextDouble();
			if(percentEdges>=0&&percentEdges<=1)
				break;
			System.out.print("Enter a positive fraction between 0 and 1:\n");
		}
		NetworkNode[] graph = new NetworkNode[numOfNodes];
		
			
		graph = RandomGraphGenerator.generateRandomGraph(numOfNodes, numberOfHospitals,percentEdges);
		//exits if no graph generated
		if(graph[0]==null) {
			System.out.println("Exiting");
			return;
		}
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
		try {
			//get file name to create a new file for outputs.
			String toWrite="";
	    	System.out.println("Please input a file name for the output of search");
	    	Scanner sc = new Scanner(System.in);
	    	String inputName = sc.nextLine();
	        File myObj = new File(inputName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File already exists.");
	          return;
	        }
	        
	        FileWriter myWriter = new FileWriter(inputName);
			int i;
			NodeInfo[] BFSInfo = new NodeInfo[numOfNodes];
			for(i=0;i<numOfNodes;i++) {
				BFSInfo[i]=new NodeInfo();
			}
			
			
			//search for each node and save output in text file.
			int j;
			for(i=0;i<numOfNodes;i++) {
				System.out.printf("Node %d\n",i);
				myWriter.write("Node "+String.valueOf(i)+"\n");
				searchNearestHospitals(graph, BFSInfo,i,numHospitals,numberOfHospitals);
				toWrite = printDist_Hospital(BFSInfo);
				myWriter.write(toWrite);
				//printPaths(network, BFSInfo);
				
				clearSearch();
				System.out.println();
				resetBFSInfo(BFSInfo,numOfNodes);
			}
			
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be read.");
		}
		 catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test on a simple graph
		/*
		NetworkNode[] network = new NetworkNode[7];
		network[0]=new NetworkNode(0);
		network[1]=new NetworkNode(1);
		network[2]=new NetworkNode(2);
		network[3]=new NetworkNode(3);
		network[4]=new NetworkNode(4);
		network[5]=new NetworkNode(5);
		network[6]=new NetworkNode(6);

		network[0].addEdge(new Node(1));
		network[0].addEdge(new Node(2));
		network[1].addEdge(new Node(3));
		network[1].addEdge(new Node(4));
		network[1].addEdge(new Node(5));
		
		network[1].addEdge(new Node(0));
		network[2].addEdge(new Node(0));
		network[2].addEdge(new Node(5));
		network[5].addEdge(new Node(2));
		network[3].addEdge(new Node(1));
		network[4].addEdge(new Node(1));
		network[5].addEdge(new Node(1));
		network[6].addEdge(new Node(5));
		network[5].addEdge(new Node(6));
		
		network[1].setHospital();
		network[2].setHospital();
		network[5].setHospital();
		
		int i;
		int size=7;
		NodeInfo[] BFSInfo = new NodeInfo[size];
		for(i=0;i<size;i++) {
			BFSInfo[i]=new NodeInfo();
		}
		for(i=0;i<size;i++) {
			System.out.printf("Node %d\n",i);
			searchNearestHospitals(network,BFSInfo,i,3,3);
			Demo_printDist_Hospital(BFSInfo);
			System.out.println();
			//printPaths(network,BFSInfo);
			
			clearSearch();
			System.out.println();
			resetBFSInfo(BFSInfo,size);
		}
		*/
		char choice;
		 char input_method;
		 int i;
		 Scanner userInput = new Scanner(System.in);
		 
	     do {
	    	 System.out.println("Choose one of the following:");
	    	 System.out.println("A. Read graph from file");
	    	 System.out.println("B. Generate random graph");
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
	    		 methodB();
	    	 }
		     System.out.println("Press any key to search another time. Otherwise, enter N");
		     choice = userInput.next().charAt(0);
	     }while ((choice!='N')&&(choice!='n'));
	}
}