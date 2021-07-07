package projetoF2;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.ST;
import java.io.Serializable;
import java.util.ArrayList;

public class BuildingMap<T extends Node> implements BuildingMapInterface <T>, Serializable {
    private SymbolGraph_P nodes = new SymbolGraph_P(".//data//nodes.txt","-");
    private EdgeWeightedGraph g = nodes.graph();

    /**
     *
     * @param Node
     * @param connected
     */
    @Override
    public void addNode(T Node, ArrayList<T> connected) {
        SymbolGraph_P n = nodes;

    }

    /**
     * vai buscar todos os nos ao grafo
     * @return retorna todos os nós do grafo
     */
    @Override
    public ArrayList<T> getAllNodes() {
        ArrayList<T> n = new ArrayList<>(); //Cria novo array list
        for(int v = 0; v < g.V() ; v++) {       //Percorre o grafo
           n.add((T) nodes.nameOf(v));          //Adiciona no AL o node
        }
        return n;                           //Retorna o AL
    }

    /**
     * vai buscar os nós pelo andar
     * @param floor andar
     * @return retorn array list dos nós por um andar
     */

    @Override
    public ArrayList<T> getAllNodesByfloor(int floor) {
        ArrayList<T> NodesByFloor = new ArrayList<>();
        for(int v = 0; v < g.V(); v++) {
            if(nodes.nameOf(v).getFloor() == floor) {
                NodesByFloor.add((T) nodes.nameOf(v));
            }
        }
         return NodesByFloor;
    }

    /**
     * vai buscar os nós por lugar
     * @param s lugar
     * @return retorna array list de nós por um lugar
     */
    @Override
    public ArrayList<T> getallNodesByPlace(String s) {
        ArrayList<T> nodesByPlace = new ArrayList<>();
        for(int v = 0; v < g.V(); v++) {
            if(nodes.nameOf(v).getLocation().compareTo(s) == 0) {
                nodesByPlace.add((T) nodes.nameOf(v));
            }
        }
        return nodesByPlace;            //retorna array list com todos os nos num dado piso
    }

    /**
     * vai buscar os nós com o floor e place
     * @param floor andar
     * @param s lugar
     * @return retorn os nós com andar = floor e s = lugar
     */
    @Override
    public ArrayList<T> getNodesByPlaceandFloor(int floor, String s) {
        ArrayList<T> nodesByPlaceAndFloor = new ArrayList<>();
        for(int v = 0; v < g.V(); v++) {
            if(nodes.nameOf(v).getLocation().compareTo(s) == 0 && floor == nodes.nameOf(v).getFloor()) {
                nodesByPlaceAndFloor.add((T) nodes.nameOf(v));
            }
        }
        return nodesByPlaceAndFloor;
    }


    /**
     * vai buscar um nó
     * @param v indice do nó
     * @return retorna o nó com aquele indice
     */
    @Override
    public T getNode(int v) {
        return (T) nodes.nameOf(v);
    }

    @Override
    public void subGraph(int floor) {
        ST<Node,Integer> st = new ST<>();
        for(int v = 0; v < this.getG().V(); v++) {
            if(this.getNodes().nameOf(v).getFloor() == floor) {

            }
        }
    }

    /**
     * vai buscar todas as edges adjacentes a partir daquele nó
     * @param t nó
     * @return um iterable de edges com as edges adjacentes
     */
    @Override
    public Iterable<Edge> getAllEdgesFromNode(T t) {
        for(int v = 0; v < g.V(); v++) {
            if(nodes.nameOf(v).compareTo(t) == 0) { //Finds the node and gets all the adjacent edges.
                return nodes.graph().adj(v);
            }
        }
        return null;
    }

    /**
     * vai buscar todos as edges adjacentes do no t e com andar floor
     * @param t nó
     * @param floor andar
     * @return arraylist de edges adjacentes daquele nó e com andar = floor
     */
    @Override
    public ArrayList<Edge> getAllEdgesFromNodeInFloor(T t, int floor) {
        Iterable<Edge> edge = null;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        for(int v = 0; v < g.V(); v++) {
            if(nodes.nameOf(v).compareTo(t) == 0) {
                if(nodes.nameOf(v).getFloor() == floor) {
                    edge = nodes.graph().adj(v);
                }
            }
        }
        if(edge != null) {
            for(Edge e :edge) {
                if(this.getNode(e.getV()).getFloor() == floor && this.getNode(e.getW()).getFloor() == floor) { //Se ambas as ligaçoes
                    edgeArrayList.add(e);
                }
            }
        }
        return edgeArrayList;
    }

    /**
     * vai buscar todas as edges adjacentes do no t com place = lugar
     * @param t nó
     * @param place lugar
     * @return arraylist de edges adjacentes daquele nó com lugar = place
     */
    @Override
    public ArrayList<Edge> getAllEdgesFromNodeInPlace(T t, String place) {
        Iterable <Edge> edge = null;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        for(int v = 0; v < g.V(); v++) {
            if(nodes.nameOf(v).compareTo(t) == 0) {
                if(nodes.nameOf(v).getLocation().compareTo(place) == 0) {
                    edge = nodes.graph().adj(v);
                    System.out.println(nodes.nameOf(v));
                }
            }
        }
        if(edge != null) {
            for( Edge e : edge) {
                if(this.getNode(e.getV()).getLocation().compareTo(place) == 0 && this.getNode(e.getW()).getLocation().compareTo(place) == 0) {
                    edgeArrayList.add(e);
                    System.out.println(e);
                }
            }
        }
        return edgeArrayList;
    }

    /**
     * retorna todas as edges adjacentes do nó com aquele andar e lugar
     * @param t nó
     * @param floor andar
     * @param place lugar
     * @return todas as edges adjacentes do nó com aquele andar e lugar
     */
    @Override
    public ArrayList<Edge> getAllEdgesFromNodeInFloorAndPlace(T t, int floor, String place) {
        Iterable <Edge> edge = null;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        for(int v = 0; v < g.V(); v++) {
            if (nodes.nameOf(v).compareTo(t) == 0) {
                edge = nodes.graph().adj(v);
            }
        }
        if(edge != null) {
            for (Edge e : edge) {
                if(this.getNode(e.getV()).getFloor() == floor && this.getNode(e.getV()).getLocation().compareTo(place) == 0 && this.getNode(e.getW()).getFloor() == floor && this.getNode(e.getW()).getLocation().compareTo(place) == 0) {
                    edgeArrayList.add(e);
                }
            }
        }
        return edgeArrayList;
    }


    public SymbolGraph_P getNodes() {
        return nodes;
    }

    public EdgeWeightedGraph getG() {
        return g;
    }
}

