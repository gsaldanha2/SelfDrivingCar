package math;

/**
 * Created by Gregory on 11/27/16.
 */
public class Vec2 {

    public float x_, y_;

    public Vec2(float x, float y) {
        x_ = x;
        y_ = y;
    }

    public void set(float x, float y) {
        x_ = x;
        y_ = y;
    }

    public String toString() {
        return x_ + ", " + y_;
    }

}
