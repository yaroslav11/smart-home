package ru.sbt.mipt.oop.utilities.processors;

import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.entities.Light;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.CommandType;
import ru.sbt.mipt.oop.utilities.SensorCommand;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import static ru.sbt.mipt.oop.utilities.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.utilities.SensorEventType.LIGHT_ON;

public class LightEventProcessing implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (!isLight(event)) return;

//        for (Room room : smartHome.getRooms()) {
//            for (Light light : room.getLights()) {
//                if (light.getId().equals(event.getObjectId())) {
//                    switchLight(event, room, light);
//                }
//            }
//        }

        smartHome.executeAction(obj ->{
            if (obj instanceof Light){
                Light light = (Light)obj;
                if (light.getId().equals(event.getObjectId())) {
                    switchLight(event, light);
                }
            }
        });
    }

    public static void switchLight(SensorEvent event, Light light) {
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " was turned on.");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " was turned off.");
        }
    }

    public static boolean isLight(SensorEvent event) {
        return  ((event.getType() == LIGHT_ON) ||  (event.getType() == LIGHT_OFF));
    }

    public void turnOffHomeLights(SmartHome smartHome){
        smartHome.executeAction(obj ->{
            if (obj instanceof Light){
                Light light = (Light)obj;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                Application.sendCommand(command);
            }
        });
    }

}
