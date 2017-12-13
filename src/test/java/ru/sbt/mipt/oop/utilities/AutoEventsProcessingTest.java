package ru.sbt.mipt.oop.utilities;

import org.junit.Test;
import ru.sbt.mipt.oop.entities.Door;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.processors.AutoEventsProcessing;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.utilities.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.utilities.SensorEventType.DOOR_OPEN;

public class AutoEventsProcessingTest {
    @Test
    public void handleNormal() {
        AutoEventsProcessing autoEventsProcessing = new AutoEventsProcessing();
        SmartHome home = new SmartHome();

        String lightId = "1";
        Light light = new Light(lightId, true);

        String doorId = "1";
        Door door = new Door(true, doorId);

        home.addRoom(new Room(Arrays.asList(light),
                Arrays.asList(door), "hall"));

        SensorEvent event = new SensorEvent(DOOR_CLOSED, doorId);
        autoEventsProcessing.handle(home, event);

        assertFalse(light.isOn());
    }

    @Test
    public void handleWrongRoom() {
        AutoEventsProcessing autoEventsProcessing = new AutoEventsProcessing();
        SmartHome home = new SmartHome();

        String lightId = "1";
        Light light = new Light(lightId, true);

        String doorId = "1";
        Door door = new Door(true, doorId);

        home.addRoom(new Room(Arrays.asList(light),
                Arrays.asList(door), "Not_a_hall"));

        SensorEvent event = new SensorEvent(DOOR_CLOSED, doorId);
        autoEventsProcessing.handle(home, event);

        assertTrue(light.isOn());
    }

    @Test
    public void handleWrongAction() {
        AutoEventsProcessing autoEventsProcessing = new AutoEventsProcessing();
        SmartHome home = new SmartHome();

        String lightId = "1";
        Light light = new Light(lightId, true);

        String doorId = "1";
        Door door = new Door(true, doorId);

        home.addRoom(new Room(Arrays.asList(light),
                Arrays.asList(door), "hall"));

        SensorEvent event = new SensorEvent(DOOR_OPEN, doorId);
        autoEventsProcessing.handle(home, event);

        assertTrue(light.isOn());
    }

}
