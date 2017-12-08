package ru.sbt.mipt.oop.entities.alarm;

import ru.sbt.mipt.oop.utilities.SensorEvent;

public interface AlarmSystem {
    AlarmSystemStateEnum getState();
    void turnOn();
    void enterPassword();
    void onEvent(SensorEvent sensorEvent);
}
