import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.io.FileNotFoundException;


public class RandomGraphGenerator {
	public static NetworkNode[] generateRandomGraph(int numberOfNode,int numberOfHospital,double percentOfEdges) {
		// TODO Auto-generated constructor stub
		NetworkNode[] tempNetwork = new NetworkNode[numberOfNode];
		try {
			//output graph into a file
			String toWrite="";
	    	System.out.println("Please input a file name with .txt for the graph information (Eg. graph.txt)");
	    	Scanner sc = new Scanner(System.in);
	    	String inputName = sc.nextLine();
	        File myObj = new File(inputName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File already exists.");
	          return tempNetwork;
	        }
	        FileWriter myWriter = new FileWriter(inputName);
	        
	        //output hospital into a file
	    	System.out.println("Please input a file name with .txt for hospital information (Eg. graph.txt)");
	    	String inputName1 = sc.nextLine();
	        File myObj1 = new File(inputName1);
	        if (myObj1.createNewFile()) {
	          System.out.println("File created: " + myObj1.getName());
	        } else {
	          System.out.println("File already exists.");
	          return tempNetwork;
	        }
	        FileWriter myWriter_hospital = new FileWriter(inputName1);
	        //Add information into graph file
	        myWriter.write("# Undirected graph (each unordered pair of nodes is saved once): "+inputName+"\n");
	        myWriter.write("# Random graph\n");
	        myWriter.write("# Nodes: "+numberOfNode+ " Edges: "+(int)(percentOfEdges*numberOfNode*(numberOfNode-1)/2)+"\n");
	        myWriter.write("# FromNodeId      ToNodeId\n");
	        
	        
	        //get number of edges
	        int numOfEdges = (int)(percentOfEdges*numberOfNode*(numberOfNode-1)/2);
			// Note: use LinkedHashSet to maintain insertion order
			LinkedHashSet<Integer> generatedNeighbour[] = new LinkedHashSet[numberOfNode];	
			//Create Nodes
			for(int i =0;i<numberOfNode ; i++) {
				tempNetwork[i]= new NetworkNode(i);
				generatedNeighbour[i]=new LinkedHashSet<Integer>();
			}
			Random rand = new Random(); //instance of random class
			int node1, node2;
			while(numOfEdges>0) {	 
				node1 = rand.nextInt(numberOfNode);
				node2 = rand.nextInt(numberOfNode);
				while((generatedNeighbour[node1].contains(node2))||(node1==node2)) {
					node1 = rand.nextInt(numberOfNode);
					node2 = rand.nextInt(numberOfNode);
				}
				generatedNeighbour[node1].add(node2);
				generatedNeighbour[node2].add(node1);
				numOfEdges--;
			}
			int i;
			int j;
			// add all the randomly generate neighbours to the node  
			for (i=0;i<numberOfNode;i++) {
				for(int temp:generatedNeighbour[i]) {
					tempNetwork[i].addEdge(new Node(temp));
					myWriter.write(i+"      "+temp+"\n");
				}
				//For debugging
				//System.out.println(String.valueOf(i)+" > "+String.valueOf(temp));
		     }
			
			//save to hospitalfile
			myWriter_hospital.write("# "+String.valueOf(numberOfHospital)+"\n");
	        //randomly set node as hospital
			Set<Integer> generatedHospital = new LinkedHashSet<Integer>();
			while (generatedHospital.size() < numberOfHospital)
			{
				//Only generate node that between node index +1 to the max node index
				int result = rand.nextInt(numberOfNode);
			    // As we're adding to a set, this will automatically do a containment check
				generatedHospital.add(result);
			}
			
			for (int temp : generatedHospital) {
				tempNetwork[temp].setHospital();
				//For debugging
				//System.out.println("Set node "+String.valueOf(temp)+" as Hospital");
				myWriter_hospital .write(String.valueOf(temp)+"\n");
		     }	
			myWriter.close();
			myWriter_hospital.close();
			System.out.println("Successfully wrote to the file.");
			return tempNetwork;
		}

		catch(FileNotFoundException e) {
			System.out.println("File cannot be read.");
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
      }
		
		

		return tempNetwork;
		
	}
	public static void main(String[] args) {

		NetworkNode[] testgraph = new NetworkNode[100];
		testgraph=generateRandomGraph(10,2,0.4);
		
		//for debugging
		/*
		for(int i=0;i<10;i++) {
			System.out.println(i);
			testgraph[i].getEdges();
			Node cur = null;
			if(testgraph[i].getEdges().size()!=0)
				cur = testgraph[i].getEdges().getFirst();
			while(cur!=null) {
				System.out.printf("%d   %d\n",i,cur.getValue());
				cur=cur.getNext();
			}
		}*/
		
		
		//RandomGraphGenerator r = new RandomGraphGenerator(1000,100);
	}

}