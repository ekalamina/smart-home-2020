package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.decorators.AlarmDecorator;
import ru.sbt.mipt.oop.decorators.SmsDecorator;
import ru.sbt.mipt.oop.handlers.EventAlarmHandler;
import ru.sbt.mipt.oop.handlers.EventDoorHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.EventLightHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class SpringConfiguration {


    @Bean
    HomeInitializer homeInitializer() {
        return new HomeJsonInitializer();
    }

    @Bean
    @Autowired
    SmartHome smartHome(HomeInitializer homeInitializer) {
        return homeInitializer.initializeHome();
    }

    @Bean
    EventHandler eventAlarmHandler() {
        return new SmsDecorator(new EventAlarmHandler());
    }

    @Bean
    EventHandler eventLightHandler() {
        return new AlarmDecorator(new EventLightHandler());
    }

    @Bean
    EventHandler eventDoorHandler() {
        return new AlarmDecorator(new EventDoorHandler());
    }

    @Bean
    Map<String, SensorEventType> eventTypeMap() {
        return new HashMap<String, SensorEventType>() {{
            put("LightIsOn", SensorEventType.LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
            put("DoorIsLocked", null);
            put("DoorIsUnlocked", null);
        }};
    }

    @Bean
    public SensorEventsManager sensorEventsManager(List<EventHandler> eventHandlers, SmartHome smartHome,
                                                   Map<String,SensorEventType> eventTypeMap) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        eventHandlers.forEach(eventHandler -> {
            sensorEventsManager.registerEventHandler(new EventHandlerAdapter(smartHome, eventHandler, eventTypeMap ));
        });

        return sensorEventsManager;
    }
}