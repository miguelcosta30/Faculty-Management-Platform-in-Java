package projetoF2;

import edu.princeton.cs.algs4.Edge;

import java.util.ArrayList;

public interface BuildingMapInterface <T> {

    void addNode(T Node, ArrayList<T> connected);
    ArrayList<T> getAllNodes();
    ArrayList<T> getAllNodesByfloor(int floor);
    ArrayList<T> getallNodesByPlace(String s);
    ArrayList<T> getNodesByPlaceandFloor(int floor, String s);
    Iterable<Edge> getAllEdgesFromNode(T t);
    T getNode(int v);
    void subGraph(int floor);
    ArrayList<Edge> getAllEdgesFromNodeInFloor(T t , int floor);
    ArrayList<Edge> getAllEdgesFromNodeInPlace(T t, String place);
    ArrayList<Edge> getAllEdgesFromNodeInFloorAndPlace(T t, int floor, String place);
}
