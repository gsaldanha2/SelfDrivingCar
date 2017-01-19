package car;

import math.CarPosition;
import math.QuickMath;
import math.Vec2;
import sun.nio.cs.ext.MacHebrew;

import java.awt.geom.Line2D;

/**
 * Created by Gregory on 11/29/16.
 */
public class MovementComputer {

    private Axle frontAxle_, backAxle_;
    private CarPosition carPosition_;
    private float angleOfCarOnCircle_;

    public MovementComputer(Car car) {
        frontAxle_ = car.getFrontAxle();
        backAxle_ = car.getBackAxle();
        carPosition_ = car.getPosition();
    }

    private float computeRotation() {
        return (frontAxle_.getRotation() > 0) ? (angleOfCarOnCircle_ + 90) : (angleOfCarOnCircle_ - 90);
    }

    private CarPosition moveDistanceOnCircle(float distance) {
        Line2D.Float perpendicularFrontLine = computeLinePerpendicularToAxle(frontAxle_);
        Line2D.Float perpendicularBackLine = computeLinePerpendicularToAxle(backAxle_);
        Vec2 circleOrigin = QuickMath.computeIntersectionOfLines(perpendicularFrontLine, perpendicularBackLine);
        if(circleOrigin == null) { return new CarPosition(carPosition_.getX() + distance * QuickMath.cos(frontAxle_.getRotation()), carPosition_.getY() + distance * QuickMath.sin(frontAxle_.getRotation()), carPosition_.getRotation()); }
        angleOfCarOnCircle_ = QuickMath.angleBetweenPoints(circleOrigin.x_, circleOrigin.y_, perpendicularBackLine.x1, perpendicularBackLine.y1);
        return new CarPosition(computeNewPositionOnCircle(distance, perpendicularBackLine, circleOrigin), computeRotation());
    }

    public CarPosition computePositionFromDistance(float distance) {
        if (frontAxle_.getRotation() == backAxle_.getRotation()) {
            return new CarPosition(carPosition_.getX() + distance * QuickMath.cos(carPosition_.getRotation()), carPosition_.getY() + distance * QuickMath.sin(carPosition_.getRotation()), carPosition_.getRotation());
        }
        return moveDistanceOnCircle(distance);
    }

    private Vec2 computeNewPositionOnCircle(float distance, Line2D.Float perpendicularbackLine, Vec2 origin) {
        float radius = QuickMath.distanceFrom(perpendicularbackLine.x1, perpendicularbackLine.y1, origin.x_, origin.y_);
        float distanceAngle = distance / (2f * radius * QuickMath.PI) * 360f;
        angleOfCarOnCircle_ = (frontAxle_.getRotation() > 0) ? (angleOfCarOnCircle_ + distanceAngle) : (angleOfCarOnCircle_ - distanceAngle);
        angleOfCarOnCircle_ %= 360f;
        return new Vec2(origin.x_ + radius * QuickMath.cos(angleOfCarOnCircle_) - backAxle_.getDistanceFromCar() * QuickMath.cos(carPosition_.getRotation()),
                origin.y_ + radius * QuickMath.sin(angleOfCarOnCircle_) - backAxle_.getDistanceFromCar() * QuickMath.sin(carPosition_.getRotation()));
    }

    private Line2D.Float computeLinePerpendicularToAxle(Axle axle) {
        float lineRotation = axle.getRotation() + carPosition_.getRotation() + 90;
        float startX = carPosition_.getX() + axle.getDistanceFromCar() * QuickMath.cos(carPosition_.getRotation());
        float startY = carPosition_.getY() + axle.getDistanceFromCar() * QuickMath.sin(carPosition_.getRotation());
        return new Line2D.Float(startX, startY, startX + 100 * QuickMath.cos(lineRotation), startY + 100 * QuickMath.sin(lineRotation));
    }

}
