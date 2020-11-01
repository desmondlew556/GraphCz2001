import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class RandomHospital {
	
	//random total number of hospitals
	public static int total=0;
	public static int RandomTotal(int totalNumberOfNodes) {
		Random rand = new Random();
		total = rand.nextInt(totalNumberOfNodes);
		return total;
	}
	//set the total number of hospitals with target percentage
	public static int setTotal(int totalNumberOfNodes,double percentage) {
		total = (int) (totalNumberOfNodes*percentage);
		return total;
	}
	// generate random hospital nodes
	public static void generateRandomHospital(int sizeOfGraph) {
		try {
			Random rand = new Random();
			File myObj = new File("RandomHospitals.txt");
			FileWriter myWriter = new FileWriter(myObj);
			myWriter.write("# "+String.valueOf(total)+"\n");
			for(int i=0;i<total;i++) {
				int hospital = rand.nextInt(sizeOfGraph);
				myWriter.write(String.valueOf(hospital)+"\n");
			}
			myWriter.close();
		}catch(IOException e) {
			System.out.println("File does not exist!");
		}
	}
	
	//delete contents of the file but not the file itself
	//Just keep using RandomHospitals.txt as you generate?
	public static void resetFile() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("RandomHospitals.txt");
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		File myObj = new File("roadNet-PA.txt");
		int size;
		try {
			size = Preprocessing.getSizeOfGraph(myObj);
			setTotal(size,0.3);
			generateRandomHospital(size);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
