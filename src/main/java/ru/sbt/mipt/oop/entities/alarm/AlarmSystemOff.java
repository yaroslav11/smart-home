package ru.sbt.mipt.oop.entities.alarm;

import ru.sbt.mipt.oop.utilities.SensorEvent;

public class AlarmSystemOff implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;

    public AlarmSystemOff(AlarmSystemState system, int password) {
        alarmSystemState =system;
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.OFF;
    }

    @Override
    public void turnOn() {
        alarmSystemState.setAlarmSystemState(new AlarmSystemOn(alarmSystemState, password));
    }

    @Override
    public void enterPassword() {
        return;
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        return;
    }
}
