package ru.sbt.mipt.oop.entities.alarm;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.utilities.SensorEvent;
import ru.sbt.mipt.oop.utilities.SensorEventType;

import static org.junit.Assert.*;

public class AlarmSystemStateWaitForPasswordTest {
    AlarmSystemState alarmSystemState;
    int password = (int)
            (new ClassPathXmlApplicationContext("application.xml")
                    .getBean("rightPassword"));

    @Before
    public void setUp() throws Exception {
        alarmSystemState = new AlarmSystemState(password);
        alarmSystemState.setAlarmSystemState(new AlarmSystemStateWaitForPassword(alarmSystemState, password));
    }

    @Test
    public void getState() throws Exception {
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

    @Test
    public void turnOn() throws Exception {
        alarmSystemState.turnOn();
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

    @Test
    public void enterRightPassword() throws Exception {
        alarmSystemState.enterPassword(password);
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.OFF);
    }

    @Test
    public void enterWrongPassword() throws Exception {
        int wrongPassword = password*2;
        // Attempt #1
        alarmSystemState.enterPassword(wrongPassword);
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
        // Attempt #2
        alarmSystemState.enterPassword(wrongPassword);
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
        // Attempt #3
        alarmSystemState.enterPassword(wrongPassword);
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.ALARM);
    }

    @Test
    public void onEvent() throws Exception {
        alarmSystemState.onEvent(new SensorEvent(SensorEventType.LIGHT_ON, "1"));
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.WAIT_FOR_PASSWORD);
    }

}