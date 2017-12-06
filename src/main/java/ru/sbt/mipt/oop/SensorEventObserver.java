package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.sbt.mipt.oop.Application.getNextSensorEvent;

public class SensorEventObserver {
    private Collection<EventHandler> eventHandlers = new ArrayList<>();
    private SmartHome smartHome;

    public SensorEventObserver(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    public void runEventCycle(){
        SensorEvent event = getNextSensorEvent();
        while (event != null) {

            EventProcessorDecorators.securedSensorEventProcessor(eventHandlers, event);
            event = getNextSensorEvent();
        }
    }

    public void addHandler(EventHandler eventHandler){
        eventHandlers.add(eventHandler);
    }

    public void setHandlers(List<EventHandler> handlers) {
        this.eventHandlers = handlers;
    }
}
