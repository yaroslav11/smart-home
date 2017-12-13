package ru.sbt.mipt.oop.entities.alarm;

import ru.sbt.mipt.oop.utilities.SensorEvent;

public class AlarmSystemStateOff implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;

    public AlarmSystemStateOff(AlarmSystemState system, int password) {
        alarmSystemState =system;
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.OFF;
    }

    @Override
    public void turnOn() {
        alarmSystemState.setAlarmSystemState(new AlarmSystemStateOn(alarmSystemState, password));
    }

    @Override
    public void enterPassword(int pinCode) {
        return;
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        return;
    }
}
