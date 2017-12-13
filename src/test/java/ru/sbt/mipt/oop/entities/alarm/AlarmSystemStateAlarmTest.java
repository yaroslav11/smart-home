package ru.sbt.mipt.oop.entities.alarm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.utilities.SensorEvent;
import ru.sbt.mipt.oop.utilities.SensorEventType;

import static org.junit.Assert.*;

public class AlarmSystemStateAlarmTest {
    AlarmSystemState alarmSystemState;
    int password = (int)
            (new ClassPathXmlApplicationContext("application.xml")
                    .getBean("rightPassword"));

    @Before
    public void setUp() throws Exception {
        alarmSystemState = new AlarmSystemState(password);
        alarmSystemState.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystemState, password));
    }

    @Test
    public void getState() throws Exception {
//        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.ALARM);
    }

    @Test
    public void turnOn() throws Exception {
        alarmSystemState.turnOn();
    }

    @Test
    public void enterPassword() throws Exception {
        alarmSystemState.enterPassword(password);
    }

    @Test
    public void onEvent() throws Exception {
        alarmSystemState.onEvent(new SensorEvent(SensorEventType.LIGHT_ON, "1"));
    }

    @After
    public void tearDown() throws Exception {
        assertEquals(alarmSystemState.getState(), AlarmSystemStateEnum.ALARM);
    }
}