package ru.sbt.mipt.oop.utilities;

import org.junit.Test;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.processors.LightEventProcessing;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.utilities.processors.LightEventProcessing.isLight;
import static ru.sbt.mipt.oop.utilities.SensorEventType.*;

public class LightEventProcessingTest {
    @Test
    public void checkHandle(){
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

    @Test
    public void checkTurnOffHomeLights(){
        LightEventProcessing lightEventProcessing = new LightEventProcessing();
        SmartHome home = new SmartHome();
        List<Light> lights = Arrays.asList(
                new Light("1", true),
                new Light("2", true),
                new Light("3", false)
        );
        home.addRoom(new Room(lights, Collections.emptyList(), "room"));
        lightEventProcessing.turnOffHomeLights(home);
        for (Light light: lights){
            assertFalse(light.isOn());
        }
    }
}
