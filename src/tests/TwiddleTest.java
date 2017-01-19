package tests;

import car.Axle;
import car.Car;
import car.MovementController;
import math.CarPosition;

import java.util.Arrays;

/**
 * Created by Gregory on 1/8/17.
 */
public class TwiddleTest {

    private final float N = 100;
    private MovementController movementController_;
    private Car car_;

    public TwiddleTest(Car car) {
        movementController_ = new MovementController(car);
        car_ = car;
        car_.setSpeed(0.52f);
    }

    public static void main(String[] args) {
        Car car = new Car(new CarPosition(0, 1, 0), new Axle(0.291f), new Axle(-0.291f));
        TwiddleTest tt = new TwiddleTest(car);
        double[] params = tt.twiddle();
        float err = tt.run(params);
        System.out.println(Arrays.toString(params) + ", err: " + err);
    }

    public double[] twiddle() {
        double tolerance = 0.00000001;
        double params[] = {0, 0, 0};
        double dparams[] = {1, 1, 1};
//        dparams[2] = 0;

        float bestErr = run(params);
        System.out.println(bestErr);
        while ((dparams[0] + dparams[1] + dparams[2]) > tolerance) {
            for (int i = 0; i < params.length; i++) {
                params[i] += dparams[i];
                float err = run(params);
                if (err < bestErr) {
                    bestErr = err;
                    dparams[i] *= 1.1f;
                } else {
                    params[i] -= 2f * dparams[i];
                    err = run(params);
                    if (err < bestErr) {
                        bestErr = err;
                        dparams[i] *= 1.1f;
                    } else {
                        params[i] += dparams[i];
                        dparams[i] *= 0.9f;
                    }
                }
            }
        }
        return params;
    }

    public float run(double[] params) {
        float totalCte = 0;
        float err = 0;
        car_.getPosition().setX(0);
        car_.getPosition().setY(0.1f);
        car_.getPosition().setRotation(0);
        float delta = 0.4f;
        float crosstrackError = car_.getPosition().getY();

        for (int i = 0; i < N; i++) {
            float diffCte = (car_.getPosition().getY() - crosstrackError) / delta;
            crosstrackError = car_.getPosition().getY();
            totalCte += crosstrackError * delta;
            car_.getFrontAxle().setRotationWithLimits((int) Math.toDegrees(-params[0] * crosstrackError - params[1] * diffCte - params[2] * totalCte));
            err += crosstrackError * crosstrackError;
            movementController_.update((int) (delta * 1000));
        }
        return err / N;
    }//[0.038409695, 0.13844731, 0.0],

}
