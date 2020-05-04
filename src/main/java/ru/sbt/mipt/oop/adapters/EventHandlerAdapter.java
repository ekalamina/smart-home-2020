package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Map;

public class EventHandlerAdapter implements EventHandler {

    private final SmartHome smartHome;
    private final ru.sbt.mipt.oop.handlers.EventHandler eventHandler;
    private final Map<String, SensorEventType> eventTypeMap;

    public EventHandlerAdapter(SmartHome smartHome, ru.sbt.mipt.oop.handlers.EventHandler eventHandler, Map<String, SensorEventType> eventTypeMap){
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
        this.eventTypeMap = eventTypeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = new SensorEvent(eventTypeMap.get(event.getEventType()), event.getObjectId());
        eventHandler.handleEvent(smartHome, sensorEvent);
    }
}
