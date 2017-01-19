package car;

import math.CarPosition;

/**
 * Created by Gregory on 11/16/16.
 */
public class Car {

    private CarPosition position_;
    private float speed_; //measured in mph
    private Axle frontAxle_;
    private Axle backAxle_;

    public Car(CarPosition position, Axle frontAxle, Axle backAxle) {
        position_ = position;
        frontAxle_ = frontAxle;
        backAxle_ = backAxle;
    }

    public void setSpeed(float speed) {
        speed_ = speed;
    }

    public CarPosition getPosition() {
        return position_;
    }

    public Axle getFrontAxle() {
        return frontAxle_;
    }

    Axle getBackAxle() {
        return backAxle_;
    }

    public float getSpeed() {
        return speed_;
    }

    void updatePosition(CarPosition position) {
        position_.setX(position.getX());
        position_.setY(position.getY());
        position_.setRotation((position.getRotation() + 360) % 360);
        System.out.println(position_.toString((int) frontAxle_.getRotation()));
    }
}
