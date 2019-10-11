import java.util.LinkedList;
import java.util.Stack;
/**
 * Class to sort a directional graph
 * 
 * @author Blake Calwell
 * @version 1.0
 */
public class IntuitiveTopological<val> implements TopologicalSort{
	
	private LinkedList<Integer> orderedList;
	private boolean isDAG;
	private LinkedList<Integer> marked;
	private Stack<Integer> onStack;
	/**
	 * Sorts the data to get a 
	 * @param graph
	 */
	public IntuitiveTopological(BetterDiGraph<val> graph) {
		isDAG=true;
		marked = new LinkedList<Integer>();
		onStack = new Stack<Integer>();
		// check for cycles
		checkCycle(graph);
		// if is a DAG
		if(isDAG) {
			// create new list
			orderedList = new LinkedList<Integer>();
			// declare new variable
			LinkedList<Integer> tempList;
			// while graph has vertex
			while(graph.getVertexCount() > 0) {
				// get list of all vertex in graph
				tempList = (LinkedList<Integer>)graph.vertices();
				// for each vertex in graph
				for(Integer id : tempList) {
					// if vertex has no predecessors
					if(graph.getIndegree(id) == 0) {
						// add vertex to list
						orderedList.add(id);
						// remove vertex
						graph.removeVertex(id);
					}
				}
			}
		}
	}
	

	
    /**
     * Returns an iterable object containing a topological sort.
     * @return a topological sort.
     */
	public Iterable<Integer> order() {
		return orderedList;
	}

    /**
     * Returns true if the graph being sorted is a DAG, false otherwise.
     * @return is graph a DAG
     */
	public boolean isDAG() {
		return isDAG;
	}
	
	private void checkCycle(BetterDiGraph<val> graph) {
		for(Integer ID : graph.vertices()) {
			if(!marked.contains(ID)) {
				checkCycle(graph, ID);
			}
		}
	}
	
	private void checkCycle(BetterDiGraph<val> graph, Integer id) {
		marked.add(id);
		onStack.add(id);
		for(Integer w : graph.getAdj(id)) {
			if(!marked.contains(w)) {
				checkCycle(graph,w);
			}
			else if(onStack.contains(w)) {
				isDAG = false;
				return;
			}
		}
		onStack.remove(id);
	}
	
}