package sensors;

import car.Car;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import math.CarPosition;
import math.QuickMath;
import math.Vec2;

import java.awt.geom.Line2D;

/**
 * Created by Gregory on 1/5/17.
 */
public class ObjectPlotter {

    private CarPosition carPosition_;

    public ObjectPlotter(CarPosition carPosition) {
        carPosition_ = carPosition;
    }

    public Vec2 getObstaclePosition( UltrasonicSensor sensor) {
        float carRotation = carPosition_.getRotation();
        updateSensorPosition(sensor, carRotation);
        return new Vec2(sensor.getX() + sensor.getReading() * QuickMath.cos(carRotation + sensor.getRotationOffset()), sensor.getY() + sensor.getReading() * QuickMath.sin(carRotation + sensor.getRotationOffset()));
    }

    private void updateSensorPosition(UltrasonicSensor sensor, float carRotation) {
        sensor.setX(carPosition_.getX() + sensor.getDistanceFromCar() * QuickMath.cos(carRotation)
                + sensor.getXOffset() * QuickMath.cos(carRotation + 90));
        sensor.setY(carPosition_.getY() + sensor.getDistanceFromCar() * QuickMath.sin(carRotation)
                + sensor.getXOffset() * QuickMath.sin(carRotation + 90));
    }

}
