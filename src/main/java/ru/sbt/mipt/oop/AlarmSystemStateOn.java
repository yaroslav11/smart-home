package ru.sbt.mipt.oop;

public class AlarmSystemStateOn implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemStateOn(AlarmSystem system) {
        alarmSystem =system;

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
    public void onEvent(SensorEvent sensorEvent) {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateWaitForPassword(alarmSystem));
    }
}
