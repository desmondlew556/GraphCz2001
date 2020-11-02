import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;
public class Preprocessing {

	public static void main(String[] args) {	
		
	}
	public static void testFile(File f1) throws FileNotFoundException{
		try (Scanner sc1 = new Scanner(f1)) {
		}
	}
    public static int setHospitalNodes(File f1, NetworkNode[] graph) throws FileNotFoundException {
		boolean first=true;
		try (Scanner sc1 = new Scanner(f1)) {
			int size=0;
			//no hospitals if numHospitals is not updated.
			int numberOfHospitals=0;
				 while (sc1.hasNextLine()) {
					 try {
						 String line = sc1.nextLine();
	                     if(first==true) {
	                         String[] splited = line.split("\\s+");
	                         size= Integer.parseInt(splited[1]);
	                         numberOfHospitals=size;
	                         first=false;
	                     }
	                     else {
	                    	if(size>0) {
	                    		size--;
	                            graph[Integer.parseInt(line)].setHospital();
							}
						 }
					 }
		             catch(Exception e) {
		            	 System.out.println("Try another file.");
		            	 return -1;
		             }
				 }
				 
				 return numberOfHospitals;
			 }
	}
    public static int getSizeOfGraph(File f1) throws FileNotFoundException {
		try (Scanner sc = new Scanner(f1)) {
			 int count = 0;
             int maxValue=0;
             int numNodes=0;
	         while (sc.hasNextLine()) {
	        	 try {
    	        	System.out.println(count);
 	            	String line = sc.nextLine();
 	            	String[] splited = line.split("\\s+");
 	            	//get the number of nodes listed
 	            	if (count==2) {
 	            		numNodes=Integer.parseInt(splited[2]);
 	            	}
 	            	if (count>3) {
                         if((Integer.parseInt(splited[0]))>maxValue)
                             maxValue=Integer.parseInt(splited[0]);
                         if((Integer.parseInt(splited[1]))>maxValue)
                             maxValue=Integer.parseInt(splited[1]);
 	            	}
 	            	count++;
	        	 }
	             catch(Exception e) {
	            	 System.out.println("Error. Try another file.");
	            	 return -1;
	             }
	        }
	         System.out.println("Size obtained.");
	         System.out.println();
	         System.out.println(count);
	         if(numNodes>(maxValue+1))
	        	 return numNodes;
	         else
	        	 return maxValue+1;
		}
    }
	public static NetworkNode[] generateGraph(File f1, int maxValue) throws FileNotFoundException {
		try (Scanner sc = new Scanner(f1)) {
            NetworkNode[] graph = new NetworkNode[maxValue];
            int count=0;
            int i;
            for(i=0;i<maxValue;i++) {
                graph[i] = new NetworkNode(i);
            }
	        while (sc.hasNextLine()) {
	        	System.out.println(count);
	            	String line = sc.nextLine();
	            	String[] splited = line.split("\\s+");
	            	if (count>3) {
                        Node x = new Node(Integer.parseInt(splited[1]));
                        graph[Integer.parseInt(splited[0])].addEdge(x);
	            	}
	            	count++;
	        }
	    System.out.println("Graph generated.");
	    System.out.println();
	    return graph;
        }
    }

}
