package ru.sbt.mipt.oop;

public class AlarmSystemState implements AlarmSystem {

    private AlarmSystem alarmSystem;
    private int password;

    public AlarmSystemState(int password){
        alarmSystem = new AlarmSystemOff(this, password);
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return alarmSystem.getState();
    }

    @Override
    public void turnOn() {
        alarmSystem.turnOn();
    }

    @Override
    public void enterPassword() {
        alarmSystem.enterPassword();
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        alarmSystem.onEvent(sensorEvent);
    }

    public void setAlarmSystemState(AlarmSystem newSystemState){
        this.alarmSystem = newSystemState;
    }

}
