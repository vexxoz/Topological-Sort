import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BetterDiGraph implements EditableDiGraph{

	class Node<val>{
		int ID;
		val Value;
		LinkedList<Integer> successors;
		LinkedList<Integer> predecessors;
		private Node(int ID, val Value) {
			this.ID = ID;
			this.Value = Value;
			successors = new LinkedList<Integer>();
			predecessors = new LinkedList<Integer>();
		}
		private void addSuccessors(int idIn) {
			successors.add(idIn);
		}
		private void addPredecessors(int idIn) {
			predecessors.add(idIn);
		}
		private void setVal(val Value) {
			this.Value = Value;
		}
		private void removeSuccessors(int idIn) {
			successors.remove(idIn);
		}
		private void removePredecessors(int idIn) {
			predecessors.remove(idIn);
		}		
	}
	
	private int vert;
	private int edges;
	private Hashtable<Integer, Node<String>> list;
	
	public BetterDiGraph() {
		vert = 0;
		edges = 0;
		list = new Hashtable<Integer, Node<String>>();
	}
	// from v -> w
	public void addEdge(int v, int w) {
		list.get(v).addSuccessors(w);
		list.get(w).addPredecessors(v);
		edges++;
	}

	public void addVertex(int v) {
		list.put(v, new Node<String>(v,""));
		vert++;
	}
	
	public void addVertexValue(int v, String in) {
		list.get(v).setVal(in);
	}

	public Iterable<Integer> getAdj(int v) {
		return list.get(v).successors;
	}

	public int getEdgeCount() {
		return edges;
	}

	public int getIndegree(int v) throws NoSuchElementException {
		return list.get(v).predecessors.size();
	}

	public int getVertexCount() {
		return vert;
	}

	public void removeEdge(int v, int w) {
		list.get(v).removeSuccessors(w);
		list.get(w).removePredecessors(v);
	}

	public void removeVertex(int v) {
		for(int id : list.get(v).successors) {
			list.get(id).removePredecessors(v);
		}
		for(int id : list.get(v).predecessors) {
			list.get(id).removeSuccessors(v);
		}
		list.remove(v);
	}

	public Iterable<Integer> vertices() {
		LinkedList<Integer> newList = new LinkedList<Integer>();
		Enumeration<Integer> keys = list.keys(); 
		while (keys.hasMoreElements()) { 
            newList.add(keys.nextElement()); 
        }
		return newList;
	}
	
}