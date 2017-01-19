package trajectory;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import math.CarPosition;
import math.QuickMath;
import math.Vec2;
import rndf.Waypoint;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Gregory on 1/3/17.
 */
public class TrajectoryShifter {

    public static Trajectory generatedShiftedTrajectory(Trajectory trajectory, float shiftDistance, float bias) {
        ArrayList<Waypoint> waypoints = trajectory.getWaypoints();
        ArrayList<Waypoint> newWaypoints = new ArrayList<>();
        if(waypoints.size() < 2) return trajectory;

        float angleToSecond = QuickMath.angleBetweenPoints(waypoints.get(0).getX(), waypoints.get(0).getY(), waypoints.get(1).getX(), waypoints.get(1).getY());
//        newWaypoints.add(new Waypoint(carPosition.getX(), carPosition.getY()));
        newWaypoints.add(new Waypoint(waypoints.get(0).getX() + shiftDistance * QuickMath.cos(90 + angleToSecond), waypoints.get(0).getY() + shiftDistance * QuickMath.sin(90 + angleToSecond)));
//        newWaypoints.get(0).addConnection(newWaypoints.get(1));
        for(int i = 1; i < waypoints.size() - 1; i++) {
            float angleToThis = QuickMath.angleBetweenPoints(waypoints.get(i -1).getX(), waypoints.get(i-1).getY(), waypoints.get(i).getX(), waypoints.get(i).getY());
            float equidistAngle = (QuickMath.angleBetweenPoints(waypoints.get(i).getX(), waypoints.get(i).getY(), waypoints.get(i-1).getX(), waypoints.get(i-1).getY()) + QuickMath.angleBetweenPoints(waypoints.get(i).getX(), waypoints.get(i).getY(),waypoints.get(i+1).getX(), waypoints.get(i+1).getY())) / 2f;
            Line2D.Float bisectingLine = new Line2D.Float(waypoints.get(i).getX(), waypoints.get(i).getY(), waypoints.get(i).getX() + QuickMath.cos(equidistAngle), waypoints.get(i).getY() + QuickMath.sin(equidistAngle));
            Vec2 intersection = QuickMath.computeIntersectionOfLines(bisectingLine, new Line2D.Float(newWaypoints.get(i-1).getX(), newWaypoints.get(i-1).getY(), newWaypoints.get(i-1).getX() + QuickMath.cos(angleToThis), newWaypoints.get(i-1).getY() + QuickMath.sin(angleToThis)));
            newWaypoints.add(new Waypoint(intersection.x_, intersection.y_));
            newWaypoints.get(i-1).setConnections(waypoints.get(i-1).getConnections());
        }

        Waypoint lastPoint = waypoints.get(waypoints.size() - 1);
        Waypoint secondToLastPoint = waypoints.get(waypoints.size() - 2);
        float angleToLast = QuickMath.angleBetweenPoints(secondToLastPoint.getX(), secondToLastPoint.getY(),lastPoint.getX(), lastPoint.getY());
        Waypoint finalWaypoint = new Waypoint(lastPoint.getX() + shiftDistance * QuickMath.cos(90 + angleToLast), lastPoint.getY() + shiftDistance * QuickMath.sin(90 + angleToLast));
        finalWaypoint.setConnections(waypoints.get(waypoints.size() - 1).getConnections());
        newWaypoints.add(finalWaypoint);
        Trajectory newTraj = new Trajectory(newWaypoints);
        newTraj.setScore(bias);
        return newTraj;
    }

}
