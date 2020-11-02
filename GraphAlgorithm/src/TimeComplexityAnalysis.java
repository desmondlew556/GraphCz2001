
//Generate random graphs, and carry out search using algorithms for 50 times.
public class TimeComplexityAnalysis {
	//number of iterations to run
	private static final int NUM_RANDOM_GRAPHS_PARTAB = 5;
	private static final int NUM_RANDOM_GRAPHS_PARTCD = 50;
	//number of nodes in graph, N, are 100, 500, 2500
	//number of edges in graph, M, is 10% 
	//number of hospitals, h, is at 30% 
	//search k-nearest hospitals, where k=1(part a and b) or k=5 (part c and d)
	private static long[] numNodesQueued_for_diff_N_M1 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_N_M1 = {0,0,0};
	
	//number of edges in graph, M, is 20% (20, 200, 2000)
	private static long[] numNodesQueued_for_diff_N_M2 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_N_M2 = {0,0,0};
	
	//number of edges in graph, M, is 40% (40, 400, 4000)
	private static long[] numNodesQueued_for_diff_N_M3 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_N_M3 = {0,0,0};
	
	//number of edges in graph, M, is 40% (40, 400, 4000)
	private static long[] numNodesQueued_for_diff_N_M4 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_N_M4 = {0,0,0};

	//number of edges in graph, M, is 40% (40, 400, 4000)
	private static long[] numNodesQueued_for_diff_N_M5 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_N_M5 = {0,0,0};
	
	//number of nodes in graph, N, are values used are 2500
	//number of edges in graph, M, is 30% 
	//number of hospitals, h, are 10%, 20%, 40%
	//search k-nearest hospitals, where k=1
	private static long[] numNodesQueued_for_diff_H_K1 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_H_K1 = {0,0,0};
	//k=5
	private static long[] numNodesQueued_for_diff_H_K2 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_H_K2 = {0,0,0};;
	//k=25
	private static long[] numNodesQueued_for_diff_H_K3 = {0,0,0};
	private static long[] numEdgesTraversed_for_diff_H_K3 = {0,0,0};

	//number of nodes in graph, N, are values used are 2500
	//number of edges in graph, M, is 30%
	//number of hospitals, h, are 0%, 10%, 20%, 40%, 100%
	//search k-nearest hospitals, where k=5
	private static long[] numNodesQueued_for_diff_H_K4 = {0,0,0,0,0};
	private static long[] numEdgesTraversed_for_diff_H_K4 = {0,0,0,0,0};
	
	private static long[] numNodesQueued_for_diff_H_K5 = {0,0,0,0,0};
	private static long[] numEdgesTraversed_for_diff_H_K5 = {0,0,0,0,0};
	
	private static long[] numNodesQueued_for_diff_H_K6 = {0,0,0,0,0};
	private static long[] numEdgesTraversed_for_diff_H_K6 = {0,0,0,0,0};
	
	public static void analyseAlgorithmForCD() {
		int i;
		int r;
		int size = 100;
		double percentEdges;
		int j;
		
		System.out.println("Computing results for first part...");
		//Create graphs and corresponding BFSInfo[]
		NetworkNode[] graph1 = new NetworkNode[size];
		NodeInfo[] BFSInfo = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo[j]=new NodeInfo();
		}
		r=0;
		//M=10%
		percentEdges=0.1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph1, BFSInfo,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M1[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M1[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTCD;

		size=500;
		NetworkNode[] graph2 = new NetworkNode[size];
		NodeInfo[] BFSInfo2 = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo2[j]=new NodeInfo();
		}
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph2, BFSInfo2,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M1[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M1[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=2500;
		NetworkNode[] graph3 = new NetworkNode[size];
		NodeInfo[] BFSInfo3 = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo3[j]=new NodeInfo();
		}
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M1[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M1[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		//M = 20%
		percentEdges=0.2;
		size=100;
		r=0;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph1, BFSInfo,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M2[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M2[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=500;
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph2, BFSInfo2,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M2[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M2[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=2500;
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M2[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M2[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		//M=40%
		percentEdges=0.4;
		size=100;
		r=0;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph1, BFSInfo,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M3[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M3[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=500;
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph2, BFSInfo2,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M3[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M3[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=2500;
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M3[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M3[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTCD;

		//M=0%
		percentEdges=0;
		size=100;
		r=0;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph1, BFSInfo,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M4[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M4[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=500;
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph2, BFSInfo2,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M4[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M4[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=2500;
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M4[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M4[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		//M=100%
		percentEdges=1;
		size=100;
		r=0;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph1, BFSInfo,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M5[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M5[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=500;
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph2, BFSInfo2,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M5[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M5[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		
		size=2500;
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, 5, (int)(size*0.3));
					numNodesQueued_for_diff_N_M5[r]+=SrchAlgorithm.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M5[r]+=SrchAlgorithm.numOfEdgesTraversed;
					SrchAlgorithm.clearSearch();
					SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
		numEdgesTraversed_for_diff_N_M5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
	
		//Print results
		int l;
		
		System.out.println("H=30% of nodes and K=5");
		System.out.println("N = 100     500     2500");
		System.out.print("M=0%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M4[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M4[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.print("M=10%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M1[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M1[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("M=20%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M2[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M2[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("M=40%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M3[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M3[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.print("M=100%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M5[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M5[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.println();
		System.out.println("Computing results for first part...");
		//Study effects of H and K on graph with N=2500 and M=30%
		
		int k;
		int count;
		size=2500;
		percentEdges=0.3;
		double[] h_values= {0.1,0.2,0.4};
		
		r=0;
		for(r=0;r<h_values.length;r++) {
			for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
					graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*h_values[r]),percentEdges);
					
					int q;
					//nearest hospital
					k=1;
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K1[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K1[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
					}
					//nearest 5 hospitals
					k=5;
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K2[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K2[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
					}
					//nearest 25 hospitals
					k=25;
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K3[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K3[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
					}
			
			}
			
			numNodesQueued_for_diff_H_K1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K1[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
			numNodesQueued_for_diff_H_K2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K2[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
			numNodesQueued_for_diff_H_K3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K3[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
			
		}
		
		
		//print results
		System.out.println("N=2500, M=30% of nodes");
		System.out.println("h = 10%     20%     40%");
		System.out.print("k=1");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K1[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K1[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("k=5");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K2[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K2[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("k=25");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K3[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K3[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.println();
	}
	public static void resetVariables() {
		int i;
		for(i=0;i<3;i++) {
			numNodesQueued_for_diff_N_M1[i]=0;
			numEdgesTraversed_for_diff_N_M1[i]=0;
			
			numNodesQueued_for_diff_N_M2[i]=0;
			numEdgesTraversed_for_diff_N_M2[i]=0;
			
			numNodesQueued_for_diff_N_M3[i]=0;
			numEdgesTraversed_for_diff_N_M3[i]=0;
			
			numNodesQueued_for_diff_H_K1[i]=0;
			numEdgesTraversed_for_diff_H_K1[i]=0;
			
			numNodesQueued_for_diff_H_K2[i]=0;
			numEdgesTraversed_for_diff_H_K2[i]=0;
			
			numNodesQueued_for_diff_H_K3[i]=0;
			numEdgesTraversed_for_diff_H_K3[i]=0;
		}
		for(i=0;i<5;i++) {
			numNodesQueued_for_diff_H_K4[i] = 0;
			numEdgesTraversed_for_diff_H_K4[i] = 0;
			
			numNodesQueued_for_diff_H_K5[i] = 0;
			numEdgesTraversed_for_diff_H_K5[i] = 0;
			
			numNodesQueued_for_diff_H_K6[i] = 0;
			numEdgesTraversed_for_diff_H_K6[i] = 0;
		}
	}
	public static void analyseAlgorithmForAB() {
		System.out.println("Computing results for first part...");
		int i;
		int r;
		int size = 10;
		double percentEdges;
		int j;
		//Create graphs and corresponding BFSInfo[]
		NetworkNode[] graph1 = new NetworkNode[size];
		NodeInfo[] BFSInfo = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo[j]=new NodeInfo();
		}
		r=0;
		//M=10%
		percentEdges=0.1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph1, BFSInfo,q);
					numNodesQueued_for_diff_N_M1[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M1[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		size=50;
		NetworkNode[] graph2 = new NetworkNode[size];
		NodeInfo[] BFSInfo2 = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo2[j]=new NodeInfo();
		}
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph2, BFSInfo2,q);
					numNodesQueued_for_diff_N_M1[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M1[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		size=250;
		NetworkNode[] graph3 = new NetworkNode[size];
		NodeInfo[] BFSInfo3 = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo3[j]=new NodeInfo();
		}
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph3, BFSInfo3,q);
					numNodesQueued_for_diff_N_M1[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M1[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		//M = 20%
		percentEdges=0.2;
		size=10;
		r=0;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph1, BFSInfo,q);
					numNodesQueued_for_diff_N_M2[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M2[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		size=50;
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph2, BFSInfo2,q);
					numNodesQueued_for_diff_N_M2[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M2[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		size=250;
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph3, BFSInfo3,q);
					numNodesQueued_for_diff_N_M2[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M2[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		//M=40%
		percentEdges=0.4;
		size=10;
		r=0;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph1 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph1, BFSInfo,q);
					numNodesQueued_for_diff_N_M3[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M3[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo,size);
				}
		}
		numNodesQueued_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		size=50;
		r=1;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph2 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph2, BFSInfo2,q);
					numNodesQueued_for_diff_N_M3[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M3[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo2,size);
				}
		}
		numNodesQueued_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		size=250;
		r=2;
		for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
				graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*0.3),percentEdges);
				int q;
				for(q=0;q<size;q++) {
					SrchAlgorithmPartA_B.searchNearestHospitals(graph3, BFSInfo3,q);
					numNodesQueued_for_diff_N_M3[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
					numEdgesTraversed_for_diff_N_M3[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
					SrchAlgorithmPartA_B.clearSearch();
					SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo3,size);
				}
		}
		numNodesQueued_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		numEdgesTraversed_for_diff_N_M3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
		
		//Print results
		int l;
		System.out.println("H=30% of nodes and K=1");
		System.out.println("N = 10     50     250");
		System.out.print("M=10%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M1[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M1[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("M=20%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M2[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M2[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("M=40%");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_N_M3[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_N_M3[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.println();
		System.out.println("Computing results for second part...");
		//Study effects of H and K on graph with N=250 and M=30%
		
		int k;
		int count;
		size=250;
		percentEdges=0.3;
		double[] h_values= {0.1,0.2,0.4};
		
		r=0;
		for(r=0;r<h_values.length;r++) {
			for(i=0;i<NUM_RANDOM_GRAPHS_PARTAB;i++) {
					graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*h_values[r]),percentEdges);
					
					int q;
					//nearest hospital
					k=1;
					for(q=0;q<size;q++) {
						SrchAlgorithmPartA_B.searchNearestHospitals(graph3, BFSInfo3,q);
						numNodesQueued_for_diff_H_K1[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K1[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
						SrchAlgorithmPartA_B.clearSearch();
						SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo3,size);
					}
					//nearest 5 hospitals
					k=5;
					for(q=0;q<size;q++) {
						SrchAlgorithmPartA_B.searchNearestHospitals(graph3, BFSInfo3,q);
						numNodesQueued_for_diff_H_K2[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K2[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
						SrchAlgorithmPartA_B.clearSearch();
						SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo3,size);
					}
					//nearest 25 hospitals
					k=25;
					for(q=0;q<size;q++) {
						SrchAlgorithmPartA_B.searchNearestHospitals(graph3, BFSInfo3,q);
						numNodesQueued_for_diff_H_K3[r]+=SrchAlgorithmPartA_B.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K3[r]+=SrchAlgorithmPartA_B.numOfEdgesTraversed;
						SrchAlgorithmPartA_B.clearSearch();
						SrchAlgorithmPartA_B.resetBFSInfo(BFSInfo3,size);
					}
					
			}
			
			numNodesQueued_for_diff_H_K1[r]/=NUM_RANDOM_GRAPHS_PARTAB;
			numEdgesTraversed_for_diff_H_K1[r]/=NUM_RANDOM_GRAPHS_PARTAB;

			numNodesQueued_for_diff_H_K2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
			numEdgesTraversed_for_diff_H_K2[r]/=NUM_RANDOM_GRAPHS_PARTAB;
			
			numNodesQueued_for_diff_H_K3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
			numEdgesTraversed_for_diff_H_K3[r]/=NUM_RANDOM_GRAPHS_PARTAB;
			
			
		}
		
		
		//print results
		System.out.println("N=250, M=30% of nodes");
		System.out.println("h = 10%     20%     40%");
		System.out.print("k=1");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K1[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K1[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("k=5");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K2[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K2[l]);

		}
		System.out.print("   Number of edges traversed\n");
		
		System.out.print("k=25");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K3[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<3;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K3[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.println();
		
	}
	public static void testEffectOfk() {
		System.out.println("Computing results for first part...");
		//Study effects of H and K on graph with N=2500 and M=30%
		
		int k;
		int count;
		int size=500;
		double percentEdges=0.3;
		double[] h_values= {0,0.1,0.2,0.4,1};
		
		int i,j;
		//initialised variables for graph
		NetworkNode[] graph3 = new NetworkNode[size];
		NodeInfo[] BFSInfo3 = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo3[j]=new NodeInfo();
		}
		
		int r=0;
		for(r=0;r<h_values.length;r++) {
			for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
					graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*h_values[r]),percentEdges);
					
					int q;
					
					//nearest 5 hospitals
					k=5;
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K4[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K4[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
					}
					
			
			}
			
			numNodesQueued_for_diff_H_K4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
		}
		//print results
		int l;
		System.out.println("N=500, M=30% of nodes");
		System.out.println("h = 0%     10%     20%     40%     100%");
		System.out.print("k=5");
		for(l=0;l<5;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K4[l]);
		}
		System.out.print("   Number of nodes queued\n     ");
		for(l=0;l<5;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K4[l]);

		}
		System.out.print("   Number of edges traversed\n");
		System.out.println();
	}
	public static void testEffectsOfkAndh(int numberOfNodes) {
		int size=numberOfNodes; int j; int r; int i; double percentEdges;
		NetworkNode[] graph3 = new NetworkNode[size];
		NodeInfo[] BFSInfo3 = new NodeInfo[size];
		for(j=0;j<size;j++) {
			BFSInfo3[j]=new NodeInfo();
		}
		int k;
		int count;
		percentEdges=0.3;
		double[] h_values= {0,0.1,0.2,0.4,1};
		
		r=0;
		for(r=0;r<h_values.length;r++) {
			for(i=0;i<NUM_RANDOM_GRAPHS_PARTCD;i++) {
					graph3 = RandomGraphGenerator1.generateRandomGraph(size, (int)(size*h_values[r]),percentEdges);
					
					int q;
					//nearest hospital
					k=1;
					
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K4[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K4[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
						
					}

					//nearest 5 hospitals
					k=5;
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K5[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K5[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
					}

					//nearest 25 hospitals
					k=25;
					for(q=0;q<size;q++) {
						SrchAlgorithm.searchNearestHospitals(graph3, BFSInfo3,q, k, (int)(size*h_values[r]));
						numNodesQueued_for_diff_H_K6[r]+=SrchAlgorithm.numOfNodesQueued;
						numEdgesTraversed_for_diff_H_K6[r]+=SrchAlgorithm.numOfEdgesTraversed;
						SrchAlgorithm.clearSearch();
						SrchAlgorithm.resetBFSInfo(BFSInfo3,size);
					}
			
			}

			numNodesQueued_for_diff_H_K4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K4[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
			numNodesQueued_for_diff_H_K5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K5[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
			numNodesQueued_for_diff_H_K6[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			numEdgesTraversed_for_diff_H_K6[r]/=NUM_RANDOM_GRAPHS_PARTCD;
			
			
		}
		
		int l;
		//print results
		
		System.out.printf("N= %d , M=0.3*n of nodes\n",size);
		System.out.println("h = 0%     10%     20%     40%     100%");
		System.out.print("k=1");
		for(l=0;l<5;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K4[l]+numEdgesTraversed_for_diff_H_K4[l]);
		}
		System.out.print("   Number of nodes queued and traversed\n");
		/*for(l=0;l<5;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K4[l]);

		}
		System.out.print("   Number of edges traversed\n");
		*/
		System.out.print("k=5");
		for(l=0;l<5;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K5[l]+numEdgesTraversed_for_diff_H_K5[l]);
		}
		System.out.print("   Number of nodes queued and traversed\n");
		/*for(l=0;l<5;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K5[l]);

		}
		System.out.print("   Number of edges traversed\n");
		*/
		System.out.print("k=25");
		for(l=0;l<5;l++) {
			System.out.printf(" %d",numNodesQueued_for_diff_H_K6[l]+numEdgesTraversed_for_diff_H_K6[l]);
		}
		System.out.print("   Number of nodes queued and traversed\n");
		/*for(l=0;l<5;l++) {
			System.out.printf(" %d",numEdgesTraversed_for_diff_H_K6[l]);

		}
		System.out.print("   Number of edges traversed\n");*/
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("For BFS with break after each hospital");
		analyseAlgorithmForCD();
		resetVariables();
		System.out.println();
		System.out.println("For BFS that search entire graph:");
		analyseAlgorithmForAB();
		resetVariables();
		testEffectOfk();
		resetVariables();
		int i;
		int[] values = {1250,2500,5000};
		for(i=0;i<values.length;i++) {
			System.out.printf("computing for n=%d\n",values[i]);
			testEffectsOfkAndh(values[i]);
		}
		System.out.println("Done");
	}

}
