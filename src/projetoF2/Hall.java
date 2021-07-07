package projetoF2;

import java.io.Serializable;

public class Hall extends Node implements Serializable {
    public Hall(int floor, String name, double x, double y, String location) {
        super(floor, name, x, y, location);
    }
}
