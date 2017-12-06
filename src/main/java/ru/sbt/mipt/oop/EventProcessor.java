package ru.sbt.mipt.oop;

import java.util.Collection;

public class EventProcessor {

    public static void sensorEventProcessor(SensorEvent event,
                                            Collection<EventHandler> eventHandlers,
                                            SmartHome smartHome) {
        System.out.println("Got event: " + event);
        for (EventHandler handle : eventHandlers) {
            handle.handle(smartHome, event);
        }
        return;
    }

    public static void alarmEventProcessor(AlarmSystemState alarmSystem){
        //
        return;
    }

}
