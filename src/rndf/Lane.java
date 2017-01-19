package rndf;

import java.util.ArrayList;

/**
 * Created by Gregory on 11/2/16.
 */
public class Lane {

    private int laneWidth_;
    private ArrayList<Waypoint> waypoints_ = new ArrayList<>();

    public Lane(int laneWidth) {
        laneWidth_ = laneWidth;
    }

    public void addWaypoint(Waypoint waypoint) {
        waypoints_.add(waypoint);
    }

}
