package ru.sbt.mipt.oop.entities.alarm;

import ru.sbt.mipt.oop.utilities.SensorEvent;

public class AlarmSystemStateOn implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;

    public AlarmSystemStateOn(AlarmSystemState system, int password) {
        alarmSystemState =system;
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ON;
    }

    @Override
    public void turnOn() {
        return;
    }

    @Override
    public void enterPassword(int pinCode) {
        return;
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        alarmSystemState.setAlarmSystemState(new AlarmSystemStateWaitForPassword(alarmSystemState, password));
    }
}
