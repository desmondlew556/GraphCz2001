import org.graphstream.graph.*;
import org.graphstream.stream.SourceBase.*;
import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.implementations.*;


public class testout {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("org.graphstream.ui", "swing"); 
		Graph graph = new SingleGraph("Random");
		graph.setStrict(false);
        graph.setAutoCreate(true);
	    Generator gen = new RandomGenerator(2,false,false);
	    gen.addSink(graph);
	    gen.begin();
	    for(int i=0; i<10; i++) {
	    	gen.nextEvents();
	    }
	    for(org.graphstream.graph.Node n:graph) {
	    	System.out.println(n.getId());
	    }
	    graph.edges().forEach(e -> {
	    	System.out.println(e.getId());
	    });
	    gen.end();
	    //graph.display();
	}

}
