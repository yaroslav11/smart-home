package ru.sbt.mipt.oop.utilities;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.entities.alarm.AlarmSystemState;
import ru.sbt.mipt.oop.utilities.processors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    public void runEventCycle(){
        SensorEvent lightEvent = new SensorEvent(SensorEventType.LIGHT_OFF, "1");

        EventHandler lightHandler = mock(LightEventProcessing.class);
        EventHandler doorHandler = mock(DoorEventProcessing.class);
        EventHandler autoHandler = mock(AutoEventsProcessing.class);

        this.sensorEventObserver.setHandlers(Arrays.asList(lightHandler, doorHandler, autoHandler));

        (new EventProcessorDecorators(this.smartHome, new AlarmSystemState(9876)))
                .securedSensorEventProcessor(sensorEventObserver.eventHandlers, lightEvent);
        verify(lightHandler).handle(any(SmartHome.class), eq(lightEvent));
        verify(doorHandler).handle(any(SmartHome.class), eq(lightEvent));
        verify(autoHandler).handle(any(SmartHome.class), eq(lightEvent));

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