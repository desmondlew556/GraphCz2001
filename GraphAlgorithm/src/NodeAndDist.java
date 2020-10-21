
public class NodeAndDist {
	int[] nodeAndDist = new int[2];
	public NodeAndDist() {
		nodeAndDist[0]=-1;
		nodeAndDist[1]=-1;
	}
	public NodeAndDist(int node, int dist) {
		nodeAndDist[0]=node;
		nodeAndDist[1]=dist;
	}
	public int[] getNodeAndDist() {
		return nodeAndDist;
	}
	public void setNodeAndDist(int node, int dist) {
		nodeAndDist[0]=node;
		nodeAndDist[1]=dist;
	}
}
