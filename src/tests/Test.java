package tests;
import arduino.Arduino;
import arduino.ArduinoController;
import car.Axle;
import car.Car;
import car.CarController;
import car.CarVision;
import maploader.ObjReader;
import math.CarPosition;
import rndf.Waypoint;
import sensors.UltrasonicSensor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Gregory on 11/13/16.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {

        ObjReader objReader = new ObjReader("res/map.obj");
        objReader.loadWaypointsAndEdges();
        ArrayList<Waypoint> waypoints = objReader.getWaypoints();
        for(Waypoint waypoint : waypoints) {
            System.out.println(waypoint.toString());
        }

        CarPosition carPosition = new CarPosition(0,0,90);
        carPosition.setWaypoint(waypoints.get(0));
        Car car = new Car(carPosition, new Axle(0.291f), new Axle(-0.291f));
        car.setSpeed(0.50f);
        CarVision carVision = new CarVision(carPosition);
//        carVision.addSensor(new UltrasonicSensor(-0.18f, 0.55f, -30));
//        carVision.addSensor(new UltrasonicSensor(0.18f, 0.55f, 30));
//        carVision.addSensor(new UltrasonicSensor(0.17f, 0, 90));
//        carVision.addSensor(new UltrasonicSensor(-0.17f, 0, -90));
//
//        CarController carController = new CarController(car, carVision, waypoints.get(waypoints.size() - 1));
        CarController carController = new CarController(car, carVision, new Waypoint(-7.1f, 0));
        ArduinoController arduinoController = new ArduinoController(car, carVision.getSensors());

        long lastTime = System.currentTimeMillis();
        while(true) {
            int delta = (int) (System.currentTimeMillis() - lastTime);
            if(delta >= 400) {
                System.out.println("Delta: " + delta);
                lastTime = System.currentTimeMillis();
                carController.update(delta > 0 ? delta : 1);
                arduinoController.update();
                System.out.println("\n");
            }
            Thread.sleep(1);
        }
    }

}
