package ru.sbt.mipt.oop.utilities.processors;

import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.entities.alarm.AlarmSystem;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import java.util.Collection;

public class EventProcessorDecorators extends EventProcessor {
    public EventProcessorDecorators(SmartHome smartHome, AlarmSystem alarmSystemState) {
        super(smartHome, alarmSystemState);
    }

    public static void securedSensorEventProcessor(Collection<EventHandler> eventHandlers, SensorEvent event){
        EventProcessor.alarmEventProcessor(event);
        EventProcessor.sensorEventProcessor(eventHandlers, event);
    }

}
