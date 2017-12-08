package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.entities.Door;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.processors.DoorEventProcessing;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.utilities.processors.DoorEventProcessing.isDoor;
import static ru.sbt.mipt.oop.utilities.SensorEventType.*;

public class DoorEventProcessingTest {
    @Test
    public void handle() {
        DoorEventProcessing doorEventProcessing = new DoorEventProcessing();
        SmartHome home = new SmartHome();
        String doorId = "1";
        Door door = new Door(false, doorId);

        String lightId = "1";
        Light light = new Light(lightId, false);

        home.addRoom(new Room(Collections.emptyList(),
                Arrays.asList(door), "room"));
        SensorEvent event = new SensorEvent(DOOR_OPEN, doorId);
        doorEventProcessing.handle(home, event);
        assertTrue(door.getIsOpen());
    }

    @Test
    public void checkIfDoorIsDoor() {
        assertTrue(isDoor(new SensorEvent(DOOR_OPEN, "Door_1")));
        assertTrue(isDoor(new SensorEvent(DOOR_CLOSED, "Door_2")));
    }

    @Test
    public void checkIfLightIsDoor() {
        assertFalse(isDoor(new SensorEvent(LIGHT_ON, "Light_1")));
        assertFalse(isDoor(new SensorEvent(LIGHT_OFF, "Light_2")));
    }


}
