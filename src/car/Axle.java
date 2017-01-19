package car;

/**
 * Created by Gregory on 11/27/16.
 */
public class Axle {

    public static final float MAX_ROT_RIGHT = 15.6f;
    public static final float MAX_ROT_LEFT = 19.2f;
    private float distanceFromCar_; //relative to car
    private int rotation_;

    public Axle(float distanceFromCar) {
        distanceFromCar_ = distanceFromCar;
        rotation_ = 0;
    }

    public void setRotationWithLimits(float deltaRot) {
        if(deltaRot > MAX_ROT_LEFT) deltaRot = MAX_ROT_LEFT;
        else if(deltaRot < -MAX_ROT_RIGHT) deltaRot = -MAX_ROT_RIGHT;
        rotation_  = (int) deltaRot;
    }

    public int getRotation() {
        return rotation_;
    }

    public float getDistanceFromCar() {
        return distanceFromCar_;
    }
}
