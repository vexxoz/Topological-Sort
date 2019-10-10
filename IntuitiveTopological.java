import java.util.LinkedList;

public class IntuitiveTopological implements TopologicalSort{

	LinkedList<Integer> orderedList;
	
	public IntuitiveTopological(BetterDiGraph graph) {
		orderedList = new LinkedList<Integer>();
	}
	
    /**
     * Returns an iterable object containing a topological sort.
     * @return a topological sort.
     */
	public Iterable<Integer> order() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns true if the graph being sorted is a DAG, false otherwise.
     * @return is graph a DAG
     */
	public boolean isDAG() {
		// TODO Auto-generated method stub
		return false;
	}
	
}