package math;

import rndf.Waypoint;

/**
 * Created by Gregory on 11/27/16.
 */
public class CarPosition {

    private float x_, y_, rotation_;
    private Waypoint waypoint_;

    public CarPosition(float x, float y, float rotation) {
        x_ = x;
        y_ = y;
        rotation_ = rotation;
    }

    public CarPosition(Vec2 vec2, float rotation) {
        x_ = vec2.x_;
        y_ = vec2.y_;
        rotation_ = rotation;
    }

    public float getX() {
        return x_;
    }

    public void setX(float x_) {
        this.x_ = x_;
    }

    public float getY() {
        return y_;
    }

    public void setY(float y_) {
        this.y_ = y_;
    }

    public float getRotation() {
        return rotation_;
    }

    public void setRotation(float rotation_) {
        this.rotation_ = rotation_;
    }

    public void increaseRotation(float rotation) {
        rotation_ += rotation;
    }

    public void increaseX(float x) {
        x_ += x;
    }

    public void increaseY(float y) {
        y_ += y;
    }

    public Waypoint getWaypoint() {
        return waypoint_;
    }

    public void setWaypoint(Waypoint waypoint) {
        waypoint_ = waypoint;
    }

    public String toString(int steeringRotation) {
        return x_ + ", " + y_ + ", " + rotation_ + " | " + steeringRotation;
    }
}
