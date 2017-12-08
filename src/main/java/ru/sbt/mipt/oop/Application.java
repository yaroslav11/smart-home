package ru.sbt.mipt.oop;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.entities.alarm.AlarmSystemState;
import ru.sbt.mipt.oop.utilities.*;
import ru.sbt.mipt.oop.utilities.processors.AutoEventsProcessing;
import ru.sbt.mipt.oop.utilities.processors.DoorEventProcessing;
import ru.sbt.mipt.oop.utilities.processors.EventHandler;
import ru.sbt.mipt.oop.utilities.processors.LightEventProcessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
//        SmartHome smartHome = HomeJsonFileReader.read();
//        SensorEventObserver sensorEventObserver = new SensorEventObserver(smartHome);
        SensorEventObserver sensorEventObserver = (SensorEventObserver) ctx.getBean("sensorEventObserver");
        AlarmSystemState alarmSystemState = (AlarmSystemState) ctx.getBean("alarmSystem");
//        configurateHandlers(sensorEventObserver);
        sensorEventObserver.runEventCycle();


    }
/*
    public static void configurateHandlers(SensorEventObserver sensorEventObserver) {
        List<EventHandler> handlers = new ArrayList<>();
        sensorEventObserver.setHandlers(handlers);
        sensorEventObserver.addHandler(new LightEventProcessing());
        sensorEventObserver.addHandler(new DoorEventProcessing());
        sensorEventObserver.addHandler(new AutoEventsProcessing());
    }
*/

    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

}
