package ru.sbt.mipt.oop.utilities.processors;

import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.entities.Door;
import ru.sbt.mipt.oop.entities.Room;
import ru.sbt.mipt.oop.entities.SmartHome;
import ru.sbt.mipt.oop.utilities.CommandType;
import ru.sbt.mipt.oop.utilities.SensorCommand;
import ru.sbt.mipt.oop.utilities.SensorEvent;

import static ru.sbt.mipt.oop.utilities.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.utilities.SensorEventType.DOOR_OPEN;

public class DoorEventProcessing implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (!isDoor(event)) return;

//        for (Room room : smartHome.getRooms()) {
//            for (Door door : room.getDoors()) {
//                if (door.getId().equals(event.getObjectId())) {
//                    switchDoor(smartHome, event, room, door);
////                    AutoEventsProcessing.switchOffLightInEmptyHome(smartHome, room);
//                }
//            }
//        }

        smartHome.executeAction(obj ->{
            if (obj instanceof Door){
                Door door = (Door) obj;
                if (door.getId().equals(event.getObjectId())) {
                    switchDoor(event, door);
                }
            }
        });
    }

    public static void switchDoor(SensorEvent event, Door door) {
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " was closed.");
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
//            AutoEventsProcessing.switchOffLightInEmptyHome(smartHome, room);
        }
    }

    public static boolean isDoor(SensorEvent event) {
        return ((event.getType() == DOOR_OPEN) || (event.getType() == DOOR_CLOSED));
    }
}
