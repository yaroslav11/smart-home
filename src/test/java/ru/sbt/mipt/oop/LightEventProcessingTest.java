package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class LightEventProcessingTest {
    @Test
    public void handle() throws Exception {
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SmartHome home = new SmartHome();
        String lightId = "1";
        Light light = new Light(lightId, false);
        home.addRoom(new Room(Arrays.asList(light),
                Collections.emptyList(), "room"));
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
        lightEventProcessing.handle(home, event);
        assertTrue(light.isOn());
    }

}
