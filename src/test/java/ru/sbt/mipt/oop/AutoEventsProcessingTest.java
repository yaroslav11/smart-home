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
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class AutoEventsProcessingTest {
    @Test
    public void handleNormal() throws Exception {
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
    public void handleWrongRoom() throws Exception {
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
    public void handleWrongAction() throws Exception {
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
