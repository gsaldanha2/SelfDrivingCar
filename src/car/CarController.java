package car;

import math.QuickMath;
import pidcontrol.SteeringPID;
import rndf.Waypoint;
import sensors.UltrasonicSensor;
import trajectory.TrajectoryController;

import java.util.ArrayList;

/**
 * Created by Gregory on 11/17/16.
 */
public class CarController {

    private Car car_;
    private CarVision carVision_;
    private TrajectoryController trajectoryController_;
    private MovementController movementController_;
    private SteeringPID steeringPID_;

    public CarController(Car car, CarVision carVision, Waypoint goal) {
        car_ = car;
        movementController_ = new MovementController(car_);
        carVision_ = carVision;
        trajectoryController_ = new TrajectoryController(car.getPosition(), carVision_, goal);
        steeringPID_ = new SteeringPID(car.getPosition());
    }

    public void update(int delta) {
        movementController_.update(delta);
        trajectoryController_.update(delta);
        steeringPID_.update(delta, trajectoryController_.getTrajectorySegment());
        car_.getFrontAxle().setRotationWithLimits(steeringPID_.getSteeringAngle());
    }
}
