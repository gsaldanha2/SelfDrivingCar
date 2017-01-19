package trajectory;

import car.CarVision;
import math.CarPosition;
import rndf.Waypoint;

/**
 * Created by Gregory on 12/7/16.
 */
public class PathPlanner {

    private TrajectoryGenerator trajectoryGenerator_ = new TrajectoryGenerator();
    private CarVision carVision_;
    private CarPosition carPosition_;
    private Trajectory trajectory_;
    private Waypoint goal_;

    public PathPlanner(CarPosition carPosition, CarVision carVision, Waypoint goal) {
        carPosition_ = carPosition;
        goal_ = goal;
        carVision_ = carVision;
    }

    private int totalDelta = 0;
    public boolean update(int delta) {
        totalDelta += delta;
        if (totalDelta > 20 || trajectory_ == null) {
            recalculatePath();
            totalDelta = 0;
            return true;
        }
        return false;
    }

    private void recalculatePath() {
        trajectoryGenerator_.setObstacles(carVision_.calculateObstacles());
        trajectory_ = trajectoryGenerator_.generateBestTrajectory(carPosition_, carPosition_.getWaypoint(), goal_);
    }

    public Trajectory getTrajectory() {
        return trajectory_;
    }

}
