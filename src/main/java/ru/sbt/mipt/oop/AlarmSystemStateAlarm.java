package ru.sbt.mipt.oop;

public class AlarmSystemStateAlarm implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;

    public AlarmSystemStateAlarm(AlarmSystemState system, int password) {
        alarmSystemState =system;
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALARM;
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
        return;
    }
}
