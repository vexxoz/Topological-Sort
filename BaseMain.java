import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Hashtable;

/**
 * Program for generating kanji component dependency order via topological sort.
 * 
 * @author Blake Calwell, Acuna
 * @version 1.0
 */
public class BaseMain {
	
    /**
     * Entry point for testing.
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	
    	// Instantiate graph
    	BetterDiGraph<String> graph = new BetterDiGraph<String>();
    	
    	// hash table only for post translation from id to character since graph is empty
    	Hashtable<Integer, String> tempy = new Hashtable<Integer, String>(); 
    	
    	BufferedReader indexReader;
        	// read data-kanji file
			indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-kanji.txt")), "UTF8"));
			// open write to file
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("newFile.txt"), "UTF-8"));
			// write to file
			out.append("Original List: ");
			// declare line variable for each line of the file
	        String line;
	        	// while there are lines to read
				while((line = indexReader.readLine()) != null)
					// if line is not comment
					if(!line.contains("#")) {
						// write line to file
						String[] temp = line.split("	");
						// get integer id
						int index = Integer.parseInt(temp[0]);
						// add to graph
						graph.addVertex(index);
						// add value to graph
						graph.addVertexValue(index, temp[1]);
						// print character of origianl list to file
						out.append(temp[1] + " ");
						// add the look up of this character so it can be translated after the graph is empty
						tempy.put(index, temp[1]);
						//System.out.println(graph.getVertexCount());
					}
			// close the reader
			indexReader.close();

        	// read data-components file
			indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-components.txt")), "ASCII"));
        	// while there are lines to read
			while((line = indexReader.readLine()) != null)
				// if line is not comment
				if(!line.contains("#")) {
					// write line to file
					String[] temp = line.split("	");
					// get integer id1
					int index1 = Integer.parseInt(temp[0]);
					// get integer id2
					int index2 = Integer.parseInt(temp[1]);
					// add edge to graph
					graph.addEdge(index1, index2);
					//System.out.println(graph.getEdgeCount());
				}
			// close the second reader
			indexReader.close();			
			
			// sort data
			IntuitiveTopological<String> top = new IntuitiveTopological<String>(graph);
//			System.out.println(top.order());
			// write to output file
			out.append("\nNew List: ");
			// for each item in new ordered list
			for(Integer i : top.order()) {
				//System.out.print(i + " ");
				// get Japanese character
				String s = tempy.get(i);
				// add translated character to output file
				out.append(s + " ");
			}
//			out.write("done");
			// close output file
			out.close();
    }
}