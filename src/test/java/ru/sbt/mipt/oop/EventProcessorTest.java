package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.entities.alarm.AlarmSystemState;
import ru.sbt.mipt.oop.utilities.processors.EventProcessor;
import ru.sbt.mipt.oop.utilities.processors.LightEventProcessing;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.utilities.SensorEventType.LIGHT_ON;

public class EventProcessorTest {
    SmartHome home;
    SensorEvent event;
    Light light;
    EventProcessor eventProcessor;

    @Before
    public void init(){
        home = new SmartHome();

        String lightId = "1";
        light = new Light(lightId, false);

        home.addRoom(new Room(Arrays.asList(light), Collections.emptyList(), "room"));

        event = new SensorEvent(LIGHT_ON, lightId);
    }

    @Test
    public void sensorEventProcessor() throws Exception {
        eventProcessor = new EventProcessor(home, null);
        EventProcessor.sensorEventProcessor(Arrays.asList(new LightEventProcessing()), event);
        assertTrue(light.isOn());
    }

    @Test(timeout = 1)
    public void alarmEventProcessor() throws Exception {
        eventProcessor = new EventProcessor(home, new AlarmSystemState(0000));
        EventProcessor.alarmEventProcessor(event);
    }

}