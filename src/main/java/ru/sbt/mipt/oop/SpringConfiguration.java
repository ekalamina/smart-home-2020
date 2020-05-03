package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.decorators.AlarmDecorator;
import ru.sbt.mipt.oop.decorators.SmsDecorator;
import ru.sbt.mipt.oop.handlers.*;

import java.util.List;


@Configuration
public class SpringConfiguration {

    private SmartHome smartHome;
    private HomeInitializer homeInitializer;

    public SpringConfiguration() {
        this.homeInitializer = new HomeJsonInitializer();
        this.smartHome = homeInitializer.initializeHome();
    }

    @Bean
    @Description("SmartHome")
    SmartHome getSmartHome() {
        return this.smartHome;
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
    public SensorEventsManager sensorEventsManager(List<EventHandler> eventHandlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        eventHandlers.forEach(eventHandler -> {
            sensorEventsManager.registerEventHandler(new EventHandlerAdapter(smartHome, eventHandler));
        });

        return sensorEventsManager;
    }
}
