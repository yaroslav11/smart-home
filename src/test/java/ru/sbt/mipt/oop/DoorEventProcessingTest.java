package ru.sbt.mipt.oop;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.DoorEventProcessing.isDoor;
import static ru.sbt.mipt.oop.LightEventProcessing.isLight;
import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventProcessingTest {
    @Test
    public void handle() throws Exception {
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
    public void checkIsDoor() throws Exception {
        assertFalse(isDoor(new SensorEvent(LIGHT_ON, "1")));
        assertFalse(isDoor(new SensorEvent(LIGHT_OFF, "2")));
        assertTrue(isDoor(new SensorEvent(DOOR_OPEN, "3")));
        assertTrue(isDoor(new SensorEvent(DOOR_CLOSED, "4")));
    }


}
