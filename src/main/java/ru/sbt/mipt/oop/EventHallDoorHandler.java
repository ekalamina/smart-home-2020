package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventHallDoorHandler implements EventHandler {

    private final SensorCommandSender sensorCommandSender;

    public EventHallDoorHandler(SensorCommandSender sensorCommandSender) {
        this.sensorCommandSender = sensorCommandSender;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            for (Room homeRoom : smartHome.getRooms()) {
                                for (Light light : homeRoom.getLights()) {
                                    light.setOn(false);
                                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                    sensorCommandSender.sendCommand(command);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
