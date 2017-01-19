package sensors;

import math.Vec2;

/**
 * Created by Gregory on 1/5/17.
 */
public class UltrasonicSensor {

    private float xOffset_, distanceFromCar_; //distance from center of car
    private float rotationOffset_;
    private float recentReading_ = 3.28f;
    private float x_, y_;

    public UltrasonicSensor(float xOffset, float distanceFromCar, float rotationOffset) {
        xOffset_ = xOffset;
        distanceFromCar_ = distanceFromCar;
        rotationOffset_ = rotationOffset;
    }

    public void setX(float x) {
        x_ = x;
    }

    public void setY(float y) {
        y_ = y;
    }

    public float getX() {
        return x_;
    }

    public float getY() {
        return y_;
    }

    public void setReading(float recentReading) {
        recentReading_ = recentReading;
    }

    public float getReading() {
        return recentReading_;
    }

    public float getXOffset() {
        return xOffset_;
    }

    public float getRotationOffset() {
        return rotationOffset_;
    }

    public float getDistanceFromCar() {
        return distanceFromCar_;
    }


}
