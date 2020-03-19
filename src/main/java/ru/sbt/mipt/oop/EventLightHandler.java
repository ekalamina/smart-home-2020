package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class EventLightHandler implements EventHandler{
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            logOn(light,room);
                        } else {
                            logOff(light,room);
                        }
                    }
                }
            }
        }
    }
    private void logOff(Light light, Room room){
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }
    private void logOn(Light light, Room room){
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

}
