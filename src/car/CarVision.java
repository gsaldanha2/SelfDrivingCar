package car;

import math.CarPosition;
import math.Vec2;
import sensors.ObjectPlotter;
import sensors.UltrasonicSensor;

import java.util.ArrayList;

/**
 * Created by Gregory on 1/6/17.
 */
public class CarVision {

    private ArrayList<UltrasonicSensor> sensors_ = new ArrayList<>();
    private ObjectPlotter plotter_;

    public CarVision(CarPosition carPosition) {
        plotter_ = new ObjectPlotter(carPosition);
    }

    public void addSensor(UltrasonicSensor sensor){
        sensors_.add(sensor);
    }

    public ArrayList<Vec2> calculateObstacles() {
        ArrayList<Vec2> obstacles = new ArrayList<>();
        for(UltrasonicSensor sensor : sensors_) {
            if(sensor.getReading() == 3.28f) continue;
            obstacles.add(plotter_.getObstaclePosition(sensor));
        }
        return obstacles;
    }

    public ArrayList<UltrasonicSensor> getSensors() {
        return sensors_;
    }
}
