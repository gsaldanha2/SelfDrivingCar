package trajectory;

import math.CarPosition;
import math.QuickMath;
import math.Vec2;
import rndf.Waypoint;

import java.awt.geom.Line2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Gregory on 1/1/17.
 */
public class TrajectoryEvaluator {

    private final static float DISTANCE_WEIGHT = 0.2f;
    private final static float SAFETY_WEIGHT = 10f;

    private ArrayList<Trajectory> trajectories_;
    private ArrayList<Vec2> obstacles_;

    private float distanceFromObstacle(Trajectory trajectory, Vec2 obstaclePos) {
        ArrayList<Waypoint> waypoints = trajectory.getWaypoints();
//        System.out.println(obstaclePos);
        float shortestDistance = 1000f;
        for (int i = 0; i < waypoints.size() - 1; i++) {
            float dist = (float) Line2D.Float.ptLineDist(waypoints.get(i).getX(), waypoints.get(i).getY(), waypoints.get(i + 1).getX(), waypoints.get(i + 1).getY(), obstaclePos.x_, obstaclePos.y_);
            if (dist < shortestDistance) {
                shortestDistance = dist;
            }
        }
        return shortestDistance;
    }

    public Trajectory bestTrajectory(Waypoint goal) {
        Trajectory bestTrajectory = null;
        float bestScore = 10000f;

        for (Trajectory trajectory : trajectories_) {
            scoreTrajectory(trajectory, goal);
            if (trajectory.getScore() < bestScore) {
                bestScore = trajectory.getScore();
                bestTrajectory = trajectory;
            }
        }
        return bestTrajectory;
    }

    private Trajectory bestShiftedTrajectory(Waypoint goal) {
        float bestScore = -10000f;
        Trajectory bestTrajectory = null;
        for (Trajectory trajectory : trajectories_) {
            scoreTrajectoryForSafety(trajectory);
            float distScore = DISTANCE_WEIGHT * getDistanceScore(trajectory, goal);
//            trajectory.setScore(trajectory.getScore() + distScore);
            if (trajectory.getScore() > bestScore) {
                bestScore = trajectory.getScore();
                bestTrajectory = trajectory;
            }
//            System.out.println(trajectory.getScore());
        }
        return bestTrajectory;
    }

    private void scoreTrajectoryForSafety(Trajectory trajectory) {
        float safetyScore = 1000;
        for(Vec2 obj : obstacles_) {
            float score = SAFETY_WEIGHT * distanceFromObstacle(trajectory, obj);
            if(safetyScore > score) {
                safetyScore = score;
            }
        }
        if(obstacles_.size() == 0) safetyScore = 0;
        trajectory.setScore(trajectory.getScore() + safetyScore);
    }

    private void scoreTrajectory(Trajectory trajectory, Waypoint goal) {
        float totalScore = DISTANCE_WEIGHT * getDistanceScore(trajectory, goal);
        trajectory.setScore(totalScore);
    }

    private float getDistanceScore(Trajectory trajectory, Waypoint goal) {
        Waypoint lastWaypoint = trajectory.getWaypoints().get(trajectory.getWaypoints().size() - 1);
        return QuickMath.distanceFrom(lastWaypoint.getX(), lastWaypoint.getY(), goal.getX(), goal.getY());
    }

    public void setTrajectories(ArrayList<Trajectory> trajectories) {
        trajectories_ = trajectories;
    }

    public void setObstacles(ArrayList<Vec2> obstacles) {
        obstacles_ = obstacles;
    }

    public Trajectory findBestShiftedTrajectory(Trajectory trajectory, CarPosition carPosition, Waypoint goal) {
//        ArrayList<Trajectory> shiftedTrajectories = new ArrayList<>();
//        shiftedTrajectories.add(trajectory);
//        trajectory.setScore(0.3f);
//        shiftedTrajectories.add(TrajectoryShifter.generatedShiftedTrajectory(trajectory, 0.2f,0));
//        shiftedTrajectories.add(TrajectoryShifter.generatedShiftedTrajectory(trajectory, 0.1f,0.2f));
//        shiftedTrajectories.add(TrajectoryShifter.generatedShiftedTrajectory(trajectory, -0.1f,0.2f));
//        shiftedTrajectories.add(TrajectoryShifter.generatedShiftedTrajectory(trajectory, -0.2f,0f));
//        trajectories_ = shiftedTrajectories;
//        Trajectory bestTrajectory = bestShiftedTrajectory(goal);
//        System.out.println("Chosen trajectory: " + shiftedTrajectories.indexOf(bestTrajectory));
//        return bestTrajectory;
        return trajectory;
    }
}
