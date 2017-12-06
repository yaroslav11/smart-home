package ru.sbt.mipt.oop;

public class AlarmSystemOn implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;

    public AlarmSystemOn(AlarmSystemState system, int password) {
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
    public void enterPassword() {
        return;
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        alarmSystemState.setAlarmSystemState(new AlarmSystemWaitForPassword(alarmSystemState, password));
    }
}
