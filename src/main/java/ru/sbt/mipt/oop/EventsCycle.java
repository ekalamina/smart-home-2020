package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class EventsCycle implements EventsCycles {

    private SensorCommandSender sensorCommandSender;
    private final List<EventHandler> handlers = Arrays.asList(new EventDoorHandler(), new EventLightHandler(),
            new EventHallDoorHandler(sensorCommandSender));

    @Override
    public void runCycle(SmartHome smartHome) {
        SensorEvent event = EventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler : handlers) {
                handler.handleEvent(smartHome, event);
            }
            event = EventGetter.getNextSensorEvent();
        }
    }
}
