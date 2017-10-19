package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class AutoEventsProcessing implements EventHandler {
    @Override
    public void handle(SmartHome smartHome, SensorEvent event) {
        if (isDoor(event)) return;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (!(door.getId().equals(event.getObjectId()))) {
                    return;
                }
                if (!(event.getType() == DOOR_CLOSED)) {
                    return;
                }
                if (!(room.getName().equals("hall"))) {
                    return;
                }
                switchOffLightInEmptyHome(smartHome, room);
            }
        }
    }

    public void switchOffLightInEmptyHome(SmartHome smartHome, Room room) {
        if (room.getName().equals("hall")) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    Application.sendCommand(command);
                }
            }
        }
    }

    public boolean isDoor(SensorEvent event) {
        if ((event.getType() != DOOR_OPEN) &&  (event.getType() != DOOR_CLOSED)){
            return true;
        }
        return false;
    }
}
