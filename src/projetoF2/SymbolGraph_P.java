/******************************************************************************
 *  Compilation:  javac SymbolGraph.java
 *  Execution:    java SymbolGraph filename.txt delimiter
 *  Dependencies: ST.java Graph.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41graph/routes.txt
 *                http://algs4.cs.princeton.edu/41graph/movies.txt
 *                http://algs4.cs.princeton.edu/41graph/moviestiny.txt
 *                http://algs4.cs.princeton.edu/41graph/moviesG.txt
 *                http://algs4.cs.princeton.edu/41graph/moviestopGrossing.txt
 *  
 *  %  java SymbolGraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  LAX
 *     PHX
 *     LAS
 *
 *  % java SymbolGraph movies.txt "/"
 *  Tin Men (1987)
 *     Hershey, Barbara
 *     Geppi, Cindy
 *     Jones, Kathy (II)
 *     Herr, Marcia
 *     ...
 *     Blumenfeld, Alan
 *     DeBoy, David
 *  Bacon, Kevin
 *     Woodsman, The (2004)
 *     Wild Things (1998)
 *     Where the Truth Lies (2005)
 *     Tremors (1990)
 *     ...
 *     Apollo 13 (1995)
 *     Animal House (1978)
 *
 * 
 *  Assumes that input file is encoded using UTF-8.
 *  % iconv -f ISO-8859-1 -t UTF-8 movies-iso8859.txt > movies.txt
 *
 ******************************************************************************/

package projetoF2;

import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  The {@code SymbolGraph} class represents an undirected graph_creator, where the
 *  vertex names are arbitrary strings.
 *  By providing mappings between string vertex names and integers,
 *  it serves as a wrapper around the
 *  {@link Graph} data type, which assumes the vertex names are integers
 *  between 0 and <em>V</em> - 1.
 *  It also supports initializing a symbol graph_creator from a file.
 *  <p>
 *  This implementation uses an {@link ST} to map from strings to integers,
 *  an array to map from integers to strings, and a {@link Graph} to store
 *  the underlying graph_creator.
 *  The <em>indexOf</em> and <em>contains</em> operations take time 
 *  proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 *  The <em>nameOf</em> operation takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SymbolGraph_P implements Serializable {
    private ST<Node, Integer> st;  // string -> index
    private Node[] keys;           // index  -> string
    private EdgeWeightedGraph graph; // the underlying graph_creator

    /**
     * Initializes a graph_creator from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     *
     * @param filename  the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolGraph_P(String filename, String delimiter) {
        st = new ST<>();
        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            String[] b = a[0].split(";");
            String[] c = a[1].split(";");
            Node n = new Node(Integer.parseInt(b[0]), b[1], Double.parseDouble(b[2]), Double.parseDouble(b[3]), b[4]); //The 2 Nodes in that Line
            Node n1 = new Node(Integer.parseInt(c[0]), c[1], Double.parseDouble(c[2]), Double.parseDouble(c[3]), c[4]);
            if (!st.contains(n))
                st.put(n, st.size());

            if (!st.contains(n1))
                st.put(n1, st.size());
        }
        StdOut.println("Done reading " + filename);

        // inverted index to get string keys in an aray
        keys = new Node[st.size()];
        for (Node name : st.keys()) {
            if(st.get(name) != null)
            keys[st.get(name)] = name;
        }

        // second pass builds the graph_creator by connecting first vertex on each
        // line to all others
        graph = new EdgeWeightedGraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter); //divides by the file delimiter. in this case will be the "-"
            String[] b = a[0].split(";");
            String[] c = a[1].split(";");
            String[] d = a[2].split(";");
            Node n = new Node(Integer.parseInt(b[0]), b[1], Double.parseDouble(b[2]), Double.parseDouble(b[3]), b[4]); //The 2 Nodes in that Line
            Node n1 = new Node(Integer.parseInt(c[0]), c[1], Double.parseDouble(c[2]), Double.parseDouble(c[3]), c[4]);
            ArrayList<Node> nodeArrayList = new ArrayList<>();
            nodeArrayList.add(n);
            nodeArrayList.add(n1);
            if (st.get(nodeArrayList.get(0)) != null) {
                int v = st.get(nodeArrayList.get(0));
                int w = st.get(nodeArrayList.get(1));
                    Edge e = new Edge(v, w, Double.parseDouble(d[0]),Double.parseDouble(d[1]));
                    graph.addEdge(e);

            }
        }
    }

    /**
     * Does the graph_creator contain the vertex named {@code s}?
     *
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(Node s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     * @deprecated Replaced by {@link #indexOf(Node)}.
     */
    @Deprecated
    public int index(Node s) {
        return st.get(s);
    }


    /**
     * Returns the integer associated with the vertex named {@code s}.
     *
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(Node s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @deprecated Replaced by {@link #nameOf(int)}.
     */
    @Deprecated
    public Node name(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     *
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Node nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    /**
     * Returns the graph_creator assoicated with the symbol graph_creator. It is the client's responsibility
     * not to mutate the graph_creator.
     *
     * @return the graph_creator associated with the symbol graph_creator
     * @deprecated Replaced by {@link #graph()}.
     */
    @Deprecated
    public EdgeWeightedGraph G() {
        return graph;
    }

    /**
     * Returns the graph_creator assoicated with the symbol graph_creator. It is the client's responsibility
     * not to mutate the graph_creator.
     *
     * @return the graph_creator associated with the symbol graph_creator
     */
    public EdgeWeightedGraph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Unit tests the {@code SymbolGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        /*String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph_P sg = new SymbolGraph_P(filename, delimiter);
        EdgeWeightedGraph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (Edge v : graph.adj(s)) {
                    StdOut.println("   " + v );
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
    }
}
    */
    }
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
