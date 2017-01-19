#include <Servo.h>
#include <NewPing.h>

#define MAX_DIST 100
#define SENSOR_COUNT 4

Servo steeringServo;
Servo drivingServo;

NewPing sensors[SENSOR_COUNT] = {
  NewPing(4, 4, MAX_DIST),
  NewPing(3, 3, MAX_DIST),
  NewPing(5,5, MAX_DIST),
  NewPing(6,6, MAX_DIST)
};

void setup() {
  Serial.begin(115200);
  Serial.setTimeout(50);

  steeringServo.attach(10);
  drivingServo.attach(9);

  drivingServo.write(95);
  steeringServo.write(90);
}

unsigned long lastTime = millis();
void loop() {
  if(millis() - lastTime > 600) {
    drivingServo.write(95);
    lastTime = millis();
  }
  process();
}

void process() {
  String text = Serial.readString();
  if(text.length() == 0) return;
  
  int steering = text.substring(0, 3).toInt();
  int motorSpeed = text.substring(3, 6).toInt();
  
  steeringServo.write(steering);
  drivingServo.write(motorSpeed);

  for(uint8_t i = 0; i < SENSOR_COUNT; i++) {
    delay(25);
    Serial.println(sensors[i].ping_in());
  }
  
  lastTime = millis();
}

