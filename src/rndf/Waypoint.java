package rndf;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gregory on 11/11/16.
 */
public class Waypoint {

    private float x_, y_;
    private ArrayList<Waypoint> connections_ = new ArrayList<>();

    public Waypoint(float x, float y) {
        x_ = x;
        y_ = y;
    }

    public ArrayList<Waypoint> getConnections() {
        return connections_;
    }

    public void setConnections(ArrayList<Waypoint> connections) {
        connections_ = connections;
    }

    public void addConnection(Waypoint edge) {
        connections_.add(edge);
    }

    public float getX() {
        return x_;
    }

    public float getY() {
        return y_;
    }

    public String toString() {
        return "\n" + x_ + ", " + y_ + "\n";
    }
}
