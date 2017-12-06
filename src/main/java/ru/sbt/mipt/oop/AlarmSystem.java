package ru.sbt.mipt.oop;

public interface AlarmSystem {
    AlarmSystemStateEnum getState();
    void turnOn();
    void enterPassword(int pinCode);
    void onEvent(SensorEvent sensorEvent);
}
