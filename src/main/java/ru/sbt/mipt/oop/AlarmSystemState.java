package ru.sbt.mipt.oop;

public interface AlarmSystemState {
    AlarmSystemStateEnum getState();

    void turnOn();

    void onEvent(SensorEvent sensorEvent);
}
