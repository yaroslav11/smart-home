package ru.sbt.mipt.oop;

public class AutoEventsProcessing {
    public static void switchOffLightInEmptyHome(SmartHome smartHome, Room room) {
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
}
