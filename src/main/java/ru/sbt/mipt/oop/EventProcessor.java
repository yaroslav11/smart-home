package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class EventProcessor {
    private static SmartHome smartHome;
    private static AlarmSystem alarmSystemState;

    protected EventProcessor(SmartHome smartHome, AlarmSystem alarmSystemState){
        this.smartHome = smartHome;
        this.alarmSystemState = alarmSystemState;
    }

    public static void sensorEventProcessor(Collection<EventHandler> eventHandlers, SensorEvent event) {
        System.out.println("Got event: " + event);
        for (EventHandler handle : eventHandlers) {
            handle.handle(smartHome, event);
        }
        return;
    }

    public static void alarmEventProcessor(SensorEvent event){
        alarmSystemState.onEvent(event);
        alarmSystemState.enterPassword();
        return;
    }


//    public class EventProcessorDecorator{
//        public static void securedSensorEventProcessor(Collection<EventHandler> eventHandlers, SensorEvent event){
//            EventProcessor.alarmEventProcessor(event);
//            EventProcessor.sensorEventProcessor(eventHandlers, event);
//        }
//    }
}
