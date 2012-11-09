#include <Servo.h>

Servo myServo;

void setup(){
  myServo.attach(9);
  Serial.begin(9600);
  while(!Serial){}
}

void loop(){
  if(Serial.available()>0){ 
    int in=Serial.read();
    myServo.write(in);
    delay(15); // wait 100ms
    Serial.write(in);
  }
}
