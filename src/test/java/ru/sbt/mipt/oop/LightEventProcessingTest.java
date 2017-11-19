package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.LightEventProcessing.isLight;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventProcessingTest {
    @Test
    public void handle(){
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SmartHome home = new SmartHome();
        String lightId = "1";
        Light light = new Light(lightId, false);
        home.addRoom(new Room(Arrays.asList(light),
                Collections.emptyList(), "room"));
        SensorEvent event = new SensorEvent(LIGHT_ON, lightId);
        lightEventProcessing.handle(home, event);
        assertTrue(light.isOn());
    }

    @Test
    public void checkIfLightIsLight() {
        assertTrue(isLight(new SensorEvent(LIGHT_ON, "Light_1")));
        assertTrue(isLight(new SensorEvent(LIGHT_OFF, "Light_2")));
    }

    @Test
    public void checkIfDoorIsLight() {
        assertFalse(isLight(new SensorEvent(DOOR_OPEN, "Door_1")));
        assertFalse(isLight(new SensorEvent(DOOR_CLOSED, "Door_2")));
    }
}
