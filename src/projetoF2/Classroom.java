package projetoF2;

import java.io.Serializable;

public class Classroom  extends Node implements Serializable {
    private int ocupation;
    private int sockets;
    public Classroom(int floor, String name, double x, double y, String location) {
        super(floor, name, x, y, location);
    }
    public int getOcupation() {
        return ocupation;
    }

    public void setOcupation(int ocupation) {
        this.ocupation = ocupation;
    }

    public int getSockets() {
        return sockets;
    }

    public void setSockets(int sockets) {
        this.sockets = sockets;
    }

    public Classroom(int floor, String name, double x, double y, String location, int ocupation, int sockets) {
        super(floor, name, x, y, location);
        this.ocupation = ocupation;
        this.sockets = sockets;
    }
}
