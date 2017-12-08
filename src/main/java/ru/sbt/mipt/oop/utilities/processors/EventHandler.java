package ru.sbt.mipt.oop.utilities.processors;

import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.SensorEvent;

public interface EventHandler {
    void handle(SmartHome smartHome, SensorEvent event);
}
