package ru.sbt.mipt.oop.utilities.processors;

import ru.sbt.mipt.oop.utilities.SensorEvent;
import ru.sbt.mipt.oop.utilities.SensorEventType;

public class ExternalEventsProcessing {

    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
