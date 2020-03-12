package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.handlers.EventHandler;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventHallDoorHandler implements EventHandler {

    private SensorCommandSender sensorCommandSender = new SensorCommandSender();

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            Collection<Door> doors = smartHome.getRoomByName("hall").getDoors();
            doors.forEach(door -> {
                if (door.getId().equals(event.getObjectId())) {
                    smartHome.execute(object -> {
                        if (object instanceof Light) {
                            Light light = (Light) object;
                            light.setOn(false);
                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                            sensorCommandSender.sendCommand(command);
                        }
                    });
                }
            });

        }
    }
}