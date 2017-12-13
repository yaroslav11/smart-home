package ru.sbt.mipt.oop.entities.alarm;

import ru.sbt.mipt.oop.utilities.SensorEvent;

public class AlarmSystemStateWaitForPassword implements AlarmSystem {
    private final AlarmSystemState alarmSystemState;
    private int password;
    private static int wrongAttemptsNumber =0;

    public AlarmSystemStateWaitForPassword(AlarmSystemState system, int password) {
        alarmSystemState =system;
        this.password = password;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.WAIT_FOR_PASSWORD;
    }

    @Override
    public void turnOn() {
        return;
    }

    @Override
    public void enterPassword(int pinCode) {
//        Scanner in = new Scanner(System.in);
//        int pinCode = in.nextInt();
        boolean rightPassword = (pinCode == password);
        if(!rightPassword){
            if (++wrongAttemptsNumber >= 3){
                wrongAttemptsNumber = 0;
                alarmSystemState.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystemState, password));
            }
        }else{
            alarmSystemState.setAlarmSystemState(new AlarmSystemStateOff(alarmSystemState, password));
        }
    }

    @Override
    public void onEvent(SensorEvent sensorEvent) {
        return;
    }
}
