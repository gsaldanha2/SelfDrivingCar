package trajectory;

import car.CarVision;
import math.CarPosition;
import rndf.Waypoint;

import java.awt.geom.Line2D;

/**
 * Created by Gregory on 12/7/16.
 */
public class TrajectoryController {

    private PathPlanner pathPlanner_;
    private Line2D.Float trajectorySegment_;
    private CarPosition carPosition_;

    public TrajectoryController(CarPosition carPosition, CarVision carVision, Waypoint goal) {
        carPosition_ = carPosition;
        pathPlanner_ = new PathPlanner(carPosition_, carVision, goal);
    }

    public void update(int delta) {
        if (pathPlanner_.update(delta)) {
            recalculateTrajectorySegment();
        }
        if(distanceAlongSegment() > 1) {
            pathPlanner_.getTrajectory().incrementWaypointIndex();
            carPosition_.setWaypoint(pathPlanner_.getTrajectory().getWaypoints().get(pathPlanner_.getTrajectory().getWaypointIndex() - 1));
            recalculateTrajectorySegment();
        }
        System.out.println("Waypoint Goal: " + pathPlanner_.getTrajectory().getWaypoints().get(pathPlanner_.getTrajectory().getWaypointIndex()));
    }

    private float distanceAlongSegment() {
        float dx = trajectorySegment_.x2 - trajectorySegment_.x1;
        float dy = trajectorySegment_.y2 - trajectorySegment_.y1;
        float rx = carPosition_.getX() - trajectorySegment_.x1;
        float ry = carPosition_.getY() - trajectorySegment_.y1;
        return (rx * dx + ry * dy) / (dx * dx + dy * dy);
    }

    private void recalculateTrajectorySegment() {
        Trajectory trajectory = pathPlanner_.getTrajectory();
        if(trajectory == null || trajectory.getWaypointIndex() >= trajectory.getWaypoints().size()) {
            System.out.println("Driving completed");
            System.exit(0);
        }
        Waypoint destinationWaypoint = trajectory.getWaypoints().get(trajectory.getWaypointIndex());
        Waypoint fromWaypoint = trajectory.getWaypoints().get(trajectory.getWaypointIndex() - 1);
        trajectorySegment_ = new Line2D.Float(fromWaypoint.getX(), fromWaypoint.getY(), destinationWaypoint.getX(), destinationWaypoint.getY());
    }

    public Line2D.Float getTrajectorySegment() {
        return trajectorySegment_;
    }

}
