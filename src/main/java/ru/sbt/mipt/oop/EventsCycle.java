package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.EventHandler;

import java.util.List;

public class EventsCycle {
    public static void runEvents(SmartHome smartHome, List<EventHandler> handlers) {
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
