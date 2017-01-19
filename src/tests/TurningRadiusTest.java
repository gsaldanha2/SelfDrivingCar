package tests;

import car.Axle;
import car.Car;
import car.MovementComputer;
import math.CarPosition;
import math.QuickMath;
import math.Vec2;

/**
 * Created by Gregory on 11/23/16.
 */
public class TurningRadiusTest {

    public static void main(String[] args) {
        Axle frontAxle = new Axle(5);
        Axle backAxle = new Axle(-5);
        CarPosition carPos = new CarPosition(10, 15, 90);
        Car car = new Car(carPos, frontAxle, backAxle);
        System.out.println("old car pos: " + carPos);
        MovementComputer tc = new MovementComputer(car);
        long st = System.currentTimeMillis();
        carPos = tc.computePositionFromDistance(74.87671f);
        System.out.println(System.currentTimeMillis() - st);
        System.out.println("new car pos: " + carPos);
        System.out.println(carPos.getX() + backAxle.getDistanceFromCar() * QuickMath.cos(carPos.getRotation()));
        System.out.println(carPos.getY() - backAxle.getDistanceFromCar() * QuickMath.sin(carPos.getRotation()));

//        float x1 = 0, y1 = 5;
//        float x2 = 0 + 100 * QuickMath.cos(40 + 180);
//        float y2 = 5 + 100 * QuickMath.sin(40 + 180);
//        float x3 = 0, y3 = -5, x4 = 100, y4 = -5;
//
//
//        Vec2 in = new Vec2(0, 0);
//        intersectLines(x1, y1, x2, y2, x3, y3, x4, y4, in);
//        System.out.println("intersection check: " + in);
    }

    public static boolean intersectLines(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, Vec2 intersection) {
        float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (d == 0) {
            return false;
        }

        if (intersection != null) {
            float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / d;
            intersection.set(x1 + (x2 - x1) * ua, y1 + (y2 - y1) * ua);
        }
        return true;
    }
}
