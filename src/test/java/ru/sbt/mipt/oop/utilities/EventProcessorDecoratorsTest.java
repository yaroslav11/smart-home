package ru.sbt.mipt.oop.utilities;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.entities.alarm.AlarmSystemState;
import ru.sbt.mipt.oop.utilities.processors.EventProcessorDecorators;
import ru.sbt.mipt.oop.utilities.processors.LightEventProcessing;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.utilities.SensorEventType.LIGHT_ON;

public class EventProcessorDecoratorsTest {
    int password = (int)
            (new ClassPathXmlApplicationContext("application.xml")
                    .getBean("rightPassword"));

    @Test(timeout = 5000)
    public void securedSensorEventProcessor() throws Exception {
        SmartHome home = new SmartHome();

        String lightId = "1";
        Light light = new Light(lightId, false);

        home.addRoom(new Room(Arrays.asList(light), Collections.emptyList(), "room"));

        SensorEvent event = new SensorEvent(LIGHT_ON, lightId);

        EventProcessorDecorators eventProcessorDecorator =
                new EventProcessorDecorators(home, new AlarmSystemState(password));
        eventProcessorDecorator.securedSensorEventProcessor(Arrays.asList(new LightEventProcessing()), event);

        assertTrue(light.isOn());
    }

}