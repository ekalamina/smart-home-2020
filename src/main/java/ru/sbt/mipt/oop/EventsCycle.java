package ru.sbt.mipt.oop;

import java.util.List;

public class EventsCycle {
    public static void EventsCycle(SmartHome smartHome, List<EventHandler> handlers) {
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
