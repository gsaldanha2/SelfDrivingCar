package maploader;

import math.Vec2;
import rndf.Waypoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gregory on 1/1/17.
 */
public class ObjReader {

    private Scanner scanner_;
    private ArrayList<Waypoint> waypoints_;

    public ObjReader(String filepath) {
        try {
            scanner_ = new Scanner(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadWaypointsAndEdges() {
        waypoints_ = new ArrayList<>();
        while(scanner_.hasNextLine()) {
            char type = scanner_.next().charAt(0);
            if(type == 'v') {
                waypoints_.add(new Waypoint(scanner_.nextFloat(), scanner_.nextFloat()));
                scanner_.nextLine();
            } else if(type == 'l') {
                int v1 = scanner_.nextInt() - 1;
                int v2 = scanner_.nextInt() - 1;
                if(v1 < v2) {
                    waypoints_.get(v1).addConnection(waypoints_.get(v2));
                }else {
                    waypoints_.get(v2).addConnection(waypoints_.get(v1));
                }
                scanner_.nextLine();
            } else {
                scanner_.nextLine();
            }
        }
    }

    public ArrayList<Waypoint> getWaypoints() {
        return waypoints_;
    }

}
