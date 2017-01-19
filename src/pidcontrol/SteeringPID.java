package pidcontrol;

import car.Car;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import math.CarPosition;
import math.QuickMath;

import java.awt.geom.Line2D;

/**
 * Created by Gregory on 11/16/16.
 */
public class SteeringPID {

    private static final double KP = 1.630236713984463, KD = 1.6802986325714722, KI = 0.01824798526194326;

    private double crossTrackError_ = 0, totalCrossTrackError = 0;
    private float steeringAngle_;
    private CarPosition carPosition_;

    public SteeringPID(CarPosition carPosition) {
        carPosition_ = carPosition;
    }

    public void update(int delta, Line2D.Float currentSegment) {
        if(currentSegment == null) return;
        updatePID(delta / 1000f, currentSegment.x2 - currentSegment.x1, currentSegment.y2 - currentSegment.y1,
                carPosition_.getX() - currentSegment.x1, carPosition_.getY() - currentSegment.y1);
//        simpleControl(currentSegment.x2, currentSegment.y2);
    }

    private void simpleControl(float xGoal, float yGoal) {
        float angleTowardsGoal = (float) (Math.toDegrees(Math.atan2(yGoal - carPosition_.getY(), xGoal - carPosition_.getX())));
        steeringAngle_ = carPosition_.getRotation() - angleTowardsGoal;
        System.out.println(angleTowardsGoal);
    }

    private void updatePID(float delta, float dx, float dy, float rx, float ry) {
        double diffCTE = -crossTrackError_;
        crossTrackError_ = (float) ((ry * dx - rx * dy) / Math.sqrt(dx * dx + dy * dy));
        diffCTE = (diffCTE + crossTrackError_) / delta;
        totalCrossTrackError += crossTrackError_ * delta;
        steeringAngle_ = (float) Math.toDegrees(-KP * crossTrackError_ - KD * diffCTE - KI * totalCrossTrackError);
        System.out.println("cte: " + crossTrackError_ * KP + ", dif: " + diffCTE * KD + ", total: " + KI * totalCrossTrackError + ", steering: " + steeringAngle_);
    }

    public float getSteeringAngle() {
        return steeringAngle_;
    }

}
