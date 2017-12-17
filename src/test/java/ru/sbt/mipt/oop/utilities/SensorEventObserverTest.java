package ru.sbt.mipt.oop.utilities;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.processors.AutoEventsProcessing;
import ru.sbt.mipt.oop.utilities.processors.DoorEventProcessing;
import ru.sbt.mipt.oop.utilities.processors.EventHandler;
import ru.sbt.mipt.oop.utilities.processors.LightEventProcessing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SensorEventObserverTest {
    SmartHome smartHome;
    SensorEventObserver sensorEventObserver;

    @Before
    public void init() {
        this.smartHome = new SmartHome();
        this.sensorEventObserver = new SensorEventObserver(this.smartHome);
    }

    @Test
    public void checkConstructor() throws Exception {
        init();
        assertEquals(this.smartHome, this.sensorEventObserver.smartHome);
    }


    @Test
    public void setHandlers() throws Exception {
        List<EventHandler> eventHandlers = new ArrayList<>();

        eventHandlers.add(new LightEventProcessing());
        eventHandlers.add(new DoorEventProcessing());
        eventHandlers.add(new AutoEventsProcessing());
        this.sensorEventObserver.setHandlers(eventHandlers);

        assertEquals(3, this.sensorEventObserver.eventHandlers.size());
        for (EventHandler handler: eventHandlers){
            assertTrue(this.sensorEventObserver.eventHandlers.contains(handler));
        }
    }

}