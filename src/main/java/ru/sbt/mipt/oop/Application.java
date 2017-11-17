package ru.sbt.mipt.oop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
//        SmartHome smartHome = HomeJsonFileReader.read();
//        SensorEventObserver sensorEventObserver = new SensorEventObserver(smartHome);
        SensorEventObserver sensorEventObserver = (SensorEventObserver) ctx.getBean("sensorEventObserver");
//        configurateHandlers(sensorEventObserver);
        sensorEventObserver.runEventCycle();

    }

    public static void configurateHandlers(SensorEventObserver sensorEventObserver) {
        List<EventHandler> handlers = new ArrayList<>();
        sensorEventObserver.setHandlers(handlers);
        sensorEventObserver.addHandler(new LightEventProcessing());
        sensorEventObserver.addHandler(new DoorEventProcessing());
        sensorEventObserver.addHandler(new AutoEventsProcessing());
    }

    static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
