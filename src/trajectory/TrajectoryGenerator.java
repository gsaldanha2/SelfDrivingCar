package trajectory;

import math.CarPosition;
import math.Vec2;
import rndf.Waypoint;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Gregory on 11/13/16.
 */
public class TrajectoryGenerator {

    private ArrayList<Trajectory> trajectories = new ArrayList<>();
    private TrajectoryEvaluator trajectoryEvaluator_ = new TrajectoryEvaluator();
    private ArrayList<Vec2> obstacles_;

    private void buildTrajectories(Waypoint waypoint) {
        trajectories.clear();
        Trajectory trajectory = new Trajectory();
        trajectory.addWaypoint(waypoint);
        expandLayer(trajectory, waypoint, 3);
    }

    private void expandLayer(Trajectory trajectory, Waypoint parentWaypoint, int depth) {
    if (depth < 1 || parentWaypoint.getConnections().size() == 0) {
            trajectories.add(trajectory);
            return;
        }
        for (Waypoint childWaypoint : parentWaypoint.getConnections()) {
            Trajectory newTraj = new Trajectory();
            newTraj.addWaypoints(trajectory.getWaypoints());
            newTraj.addWaypoint(childWaypoint);
            expandLayer(newTraj, childWaypoint, depth - 1);
        }
    }

    public Trajectory generateBestTrajectory(CarPosition carPosition, Waypoint start, Waypoint goal) {
        buildTrajectories(start);
        trajectoryEvaluator_.setObstacles(obstacles_);
        trajectoryEvaluator_.setTrajectories(trajectories);
        Trajectory bestTrajectory = trajectoryEvaluator_.bestTrajectory(goal);
        return trajectoryEvaluator_.findBestShiftedTrajectory(bestTrajectory, carPosition, goal);
    }

    public void setObstacles(ArrayList<Vec2> obstacles) {
        obstacles_ = obstacles;
    }
}
