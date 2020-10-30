import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
public class RandomGraphGenerator {
	NetworkNode[] network;
	public RandomGraphGenerator(int numberOfNode,int numberOfHospital) {
		// TODO Auto-generated constructor stub
		NetworkNode[] tempNetwork = new NetworkNode[numberOfNode];
		//Create Nodes
		for(int i =0;i<numberOfNode ; i++) {
			tempNetwork[i]= new NetworkNode(i);
		}
		Random rand = new Random(); //instance of random class
		// Note: use LinkedHashSet to maintain insertion order
				 
		for(int i= 0;i<numberOfNode; i++) {
			//generate random values from 0-numberOfNode
			int lowerB = i+1;
			int upperB = numberOfNode;				
			//Generate the number of degree(neighbour) randomly
			int degreeOfNode = rand.nextInt(numberOfNode-i);
			//Add each neighbour in a set to avoid duplication
			Set<Integer> generatedNeighbour = new LinkedHashSet<Integer>();
			
			while (generatedNeighbour.size() < degreeOfNode)
			{
				//Only generate node that between node index +1 to the max node index				
				int result = rand.nextInt(upperB-lowerB) + lowerB;				
			    // As we're adding to a set, this will automatically do a containment check
				generatedNeighbour.add(result);
			}
			// add all the randomly generate neighbours to the node  
			for (int temp : generatedNeighbour) {
				tempNetwork[i].addEdge(new Node(temp));
				tempNetwork[temp].addEdge(new Node(i));
				System.out.println(String.valueOf(i)+" > "+String.valueOf(temp));
		     }
		
		}
		
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
			System.out.println("Set node "+String.valueOf(temp)+" as Hospital");
	     }	
		network =tempNetwork;
		
	}
	public NetworkNode[] getNetwork() {
		return network;
	}
	public static void main(String[] args) {
		RandomGraphGenerator r = new RandomGraphGenerator(1000,100);
	}

}
