package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class EventProcessorDecoratorsTest {
    @Test(timeout = 1)
    public void securedSensorEventProcessor() throws Exception {
        SmartHome home = new SmartHome();

        String lightId = "1";
        Light light = new Light(lightId, false);

        home.addRoom(new Room(Arrays.asList(light), Collections.emptyList(), "room"));

        SensorEvent event = new SensorEvent(LIGHT_ON, lightId);

        EventProcessorDecorators eventProcessorDecorator =
                new EventProcessorDecorators(home, new AlarmSystemState(0000));
        EventProcessorDecorators.securedSensorEventProcessor(Arrays.asList(new LightEventProcessing()), event);

        assertTrue(light.isOn());
    }

}