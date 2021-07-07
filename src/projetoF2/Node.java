package projetoF2;

import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable {
    private String name;
    private int floor;
    private double x;
    private double y;
    private String location; // Indoor/Outdoor

    @Override
    public String toString() {
        return "Node{" +
                "floor=" + floor +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y + "location:" +
                location +
                '}';
    }

    public Node(int floor, String name, double x, double y, String location) {
        this.floor = floor;
        this.name = name;
        this.x = x;
        this.y = y;
        this.location = location;
    }
    public Node( double x, double y) {
        this.y = y;
        this.x = x;
    }
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public int compareTo(Node o) {
        if(o.getName().compareTo(this.getName()) == 0) {
            return 0;
        }
        else if(o.getName().compareTo(this.getName()) > 0) {
            return 1;
        }
        else return -1;
    }
}
