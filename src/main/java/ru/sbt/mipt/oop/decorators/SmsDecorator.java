package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.alarm.ActivatedAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlertAlarmState;
import ru.sbt.mipt.oop.handlers.EventHandler;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SmsDecorator implements EventHandler {

    private List<EventHandler> eventHandlers;

    public SmsDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent sensorEvent) {
        Alarm alarm = smartHome.getAlarm();
        if (alarm.getState() instanceof AlertAlarmState) {
            System.out.println("SMS");
            return;
        }
        eventHandlers.forEach(eventHandler -> {
            eventHandler.handleEvent(smartHome,sensorEvent);
        });
        if (alarm.getState() instanceof ActivatedAlarmState){
            alarm.alert();
            System.out.println("SMS");
        }

    }
    private boolean isLightOrDoorEvent(SensorEvent sensorEvent){
        return sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF ||
                sensorEvent.getType() == DOOR_OPEN || sensorEvent.getType() == DOOR_CLOSED;
    }

}

