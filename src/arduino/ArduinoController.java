package arduino;

import car.Axle;
import car.Car;
import com.fazecast.jSerialComm.SerialPort;
import sensors.UltrasonicSensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gregory on 12/14/16.
 */
public class ArduinoController extends Thread {

    private static final float maxSpeed = .50f;
    private Car car_;
    private BufferedReader reader_;
    private PrintWriter writer_;
    private ArrayList<UltrasonicSensor> sensors_;

    public ArduinoController(Car car, ArrayList<UltrasonicSensor> sensors) throws InterruptedException {
        setup();
        car_ = car;
        sensors_ = sensors;
        start();
    }

    private void setup() throws InterruptedException {
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Select a port:");
        int i = 1;
        for (SerialPort port : ports)
            System.out.println(i++ + ": " + port.getSystemPortName());
        Scanner s = new Scanner(System.in);
        int chosenPort = s.nextInt();

        SerialPort serialPort = ports[chosenPort - 1];
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        if (serialPort.openPort()) {
            System.out.println("Port opened successfully.");
        } else {
            System.out.println("Unable to open the port.");
            return;
        }
        Thread.sleep(100);
        serialPort.setBaudRate(115200);
        writer_ = new PrintWriter(serialPort.getOutputStream());
        reader_ = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
    }

    private String getDrivingMotorSpeed(float carSpeedMPH) {
        float speed = 90f * (carSpeedMPH / maxSpeed) + 95;
        return (speed > 180) ? "180" : String.format("%03d", (int) speed);
    }

    private String getSteeringAmount(float steeringAngle) {
        if(steeringAngle < 0) {
            return String.format("%03d", (int) ((85 + (steeringAngle / Axle.MAX_ROT_RIGHT) * 90f)));
        } else {
            return String.format("%03d", (int) ((85 + (steeringAngle / Axle.MAX_ROT_LEFT) * 90f)));
        }
    }

    public void update() throws IOException, InterruptedException {
        writer_.print(getSteeringAmount(car_.getFrontAxle().getRotation()) + "" + getDrivingMotorSpeed(car_.getSpeed()));
        writer_.flush();

        long st = System.currentTimeMillis();
        for (UltrasonicSensor sensor : sensors_) {
            sensor.setReading(Integer.parseInt(reader_.readLine()) * 0.08333f);
            System.out.println(sensor.getXOffset() + " sensor: " + sensor.getReading());
            if (sensor.getReading() == 0) {
                sensor.setReading(3.28f);
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - st));
    }

}
