package car;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import math.CarPosition;
import math.QuickMath;

/**
 * Created by Gregory on 11/27/16.
 */
public class MovementController {

    private MovementComputer movementComputer_;
    private Car car_;

    public MovementController(Car car) {
        car_ = car;
        movementComputer_ = new MovementComputer(car_);
    }

    public void update(int delta) {
//        car_.getFrontAxle().setRotationWithLimits(30);
        CarPosition position = movementComputer_.computePositionFromDistance(delta * QuickMath.MPHToFeetPerMillis(car_.getSpeed()));
        car_.updatePosition(position);
    }
}
