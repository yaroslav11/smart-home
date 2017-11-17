package ru.sbt.mipt.oop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlarmSystemTest {
    @Test
    public void testNewSystemIsOff(){
        AlarmSystem alarmSystem = new AlarmSystem();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testNewSystemIsOn(){
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void testSensorEventWaitsForPasswordState(){
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onEvent(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void testOnEventWhenSystemIsOff(){
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.onEvent(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testTurmOnDoesNothingWhenSystemWaitsForPassword(){
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        alarmSystem.onEvent(createSensorEvent());
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    public SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }
}
