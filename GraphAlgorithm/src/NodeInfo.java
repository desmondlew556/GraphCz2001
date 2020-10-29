import java.util.ArrayList;
public class NodeInfo {
	private int dist;
	private ArrayList<Integer> predecessorList;
	//get hospital nodes already visited in a particular path to avoid looping back to the same hospital
	public NodeInfo() {
		dist=Integer.MAX_VALUE;
		predecessorList=new ArrayList<Integer>();
	}
	public void setDist(int distance) {
		dist=distance;
	}
	public void addPredecessor(int predecessorVal) {
		predecessorList.add(predecessorVal);
	}
	public void reset() {
		dist=Integer.MAX_VALUE;
		predecessorList.clear();
	}
	public int getDist() {
		return dist;
	}
	public ArrayList<Integer> getPredecessor() {
		return predecessorList;
	}
}
