package ru.sbt.mipt.oop;

public interface AlarmSystem {
    AlarmSystemStateEnum getState();
    void turnOn();
    void enterPassword();
    void onEvent(SensorEvent sensorEvent);
}
