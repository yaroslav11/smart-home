package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.AlarmSystemStateEnum.OFF;
import static ru.sbt.mipt.oop.AlarmSystemStateEnum.ON;
import static ru.sbt.mipt.oop.AlarmSystemStateEnum.WAIT_FOR_PASSWORD;

public class AlarmSystem {

    private AlarmSystemStateEnum systemStateEnum = OFF;

    public AlarmSystemStateEnum getState() {
        return systemStateEnum;
    }

    public void turnOn() {
        if (systemStateEnum == WAIT_FOR_PASSWORD) return;
        systemStateEnum = ON;
    }

    public void onEvent(SensorEvent sensorEvent) {
        if(systemStateEnum == OFF) return;
        systemStateEnum = AlarmSystemStateEnum.WAIT_FOR_PASSWORD;
    }
}
