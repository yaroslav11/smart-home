package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessing implements EventHandler{
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (isDoor(event)) return;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    switchDoor(smartHome, event, room, door);
                    AutoEventsProcessing.switchOffLightInEmptyHome(smartHome, room);
                }
            }
        }
    }

    public static void switchDoor(SmartHome smartHome, SensorEvent event, Room room, Door door) {
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
//            AutoEventsProcessing.switchOffLightInEmptyHome(smartHome, room);
        }
    }

    public static boolean isDoor(SensorEvent event) {
        if ((event.getType() != DOOR_OPEN) &&  (event.getType() != DOOR_CLOSED)){
            return true;
        }
        return false;
    }
}
