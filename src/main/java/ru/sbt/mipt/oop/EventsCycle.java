package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.*;

import java.util.List;

public class EventsCycle implements EventsCycles {

    private EventGetter eventGetter;
    private final List<EventHandler> handlers;

    public EventsCycle(List<EventHandler> handlers){
        this.handlers = handlers;
        this.eventGetter = new EventGetterImplementation();
    }

    @Override
    public void runCycle(SmartHome smartHome) {
        SensorEvent event = eventGetter.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler : handlers) {
                handler.handleEvent(smartHome, event);
            }
            event = eventGetter.getNextSensorEvent();
        }
    }
}