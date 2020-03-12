package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventHallDoorHandler implements EventHandler {

    private SensorCommandSender sensorCommandSender;

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            Room room = smartHome.getRoomByName("hall");
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
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
