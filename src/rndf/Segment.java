package rndf;

import java.util.ArrayList;

/**
 * Created by Gregory on 11/3/16.
 */
public class Segment {

    private ArrayList<Lane> lanes_ = new ArrayList<>(); //left to right lanes

    public void addLane(Lane lane) {
        lanes_.add(lane);
    }

}
