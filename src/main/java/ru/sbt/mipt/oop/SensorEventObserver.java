package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

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
            System.out.println("Got event: " + event);
            for (EventHandler handle : eventHandlers) {
                handle.handle(smartHome, event);
            }
            event = getNextSensorEvent();
        }
    }

    public void addHandler(EventHandler eventHandler){
        eventHandlers.add(eventHandler);
    }
}
