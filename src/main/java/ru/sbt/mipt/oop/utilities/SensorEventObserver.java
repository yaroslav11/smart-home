package ru.sbt.mipt.oop.utilities;

import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.processors.EventHandler;
import ru.sbt.mipt.oop.utilities.processors.EventProcessorDecorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.sbt.mipt.oop.utilities.processors.ExternalEventsProcessing.getNextSensorEvent;

public class SensorEventObserver {
    Collection<EventHandler> eventHandlers = new ArrayList<>();
    SmartHome smartHome;

    public SensorEventObserver(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void runEventCycle() {
        SensorEvent event = getNextSensorEvent();
        while (event != null) {

            EventProcessorDecorators.securedSensorEventProcessor(eventHandlers, event);
            event = getNextSensorEvent();
        }
    }

//    public void addHandler(EventHandler eventHandler) {
//        eventHandlers.add(eventHandler);
//    }

    public void setHandlers(List<EventHandler> handlers) {
        this.eventHandlers = handlers;
    }
}
