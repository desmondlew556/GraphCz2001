
public class NodeInfo {
	private int dist;
	private int predecessor;
	public NodeInfo() {
		dist=-1;
		predecessor=-1;
	}
	public void setDist(int distance) {
		dist=distance;
	}
	public void setPredecessor(int predecessorVal) {
		predecessor=predecessorVal;
	}
	public int getDist() {
		return dist;
	}
	public int getPredecessor() {
		return predecessor;
	}
}
