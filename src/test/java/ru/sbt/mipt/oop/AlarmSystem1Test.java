package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlarmSystem1Test {
    @Test
    public void testNewSystemIsOff(){
        AlarmSystemState alarmSystemState = new AlarmSystemState(1234);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystemState.getState());
    }

    @Test
    public void testNewSystemIsOn(){
        AlarmSystemState alarmSystemState = new AlarmSystemState(1234);
        alarmSystemState.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystemState.getState());
    }

    @Test
    public void testSensorEventWaitsForPasswordState(){
        AlarmSystemState alarmSystemState = new AlarmSystemState(1234);
        alarmSystemState.turnOn();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystemState.onEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystemState.getState());
    }

    @Test
    public void testOnEventWhenSystemIsOff(){
        AlarmSystemState alarmSystemState = new AlarmSystemState(1234);
        alarmSystemState.onEvent(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystemState.getState());
    }

    @Test
    public void testTurmOnDoesNothingWhenSystemWaitsForPassword(){
        AlarmSystemState alarmSystemState1 = new AlarmSystemState(1234);
        alarmSystemState1.turnOn();
        alarmSystemState1.onEvent(createSensorEvent());
        alarmSystemState1.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystemState1.getState());
    }

    public SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }
}
