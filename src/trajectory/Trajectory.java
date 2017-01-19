package trajectory;

import rndf.Waypoint;

import java.awt.geom.Line2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gregory on 11/13/16.
 */
public class Trajectory {

    private ArrayList<Waypoint> waypoints_ = new ArrayList<>();
    private int waypointIndex_ = 1;
    private float score_;

    public Trajectory() {

    }

    public Trajectory(ArrayList<Waypoint> waypoints) {
        waypoints_ = waypoints;
    }

    public void addWaypoint(Waypoint waypoint) {
        waypoints_.add(waypoint);
    }

    public void addWaypoints(ArrayList<Waypoint> waypoints) {
        waypoints_.addAll(waypoints);
    }

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints_;
    }

    public void incrementWaypointIndex() {
        waypointIndex_++;
    }

    public int getWaypointIndex() {
        return waypointIndex_;
    }

    public String toString() {
        String str = "";
        for(Waypoint waypoint : waypoints_) {
            str += "\n";
            str += waypoint.getX() + ", " + waypoint.getY();
        }
        return str;
    }

    public float getScore() {
        return score_;
    }

    public void setScore(float score) {
        score_ = score;
    }
}
