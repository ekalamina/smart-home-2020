package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import ru.sbt.mipt.oop.handlers.EventHandler;

public class SpringConfiguration {

    private EventsCycles eventsCycles;
    private SmartHome smartHome;
    private HomeInitializer homeInitializer;

    public SpringConfiguration(){
        this.homeInitializer = new HomeJsonInitializer();
        this.smartHome = homeInitializer.initializeHome();
        this.eventsCycles = new EventsCycle();
    }
    @Bean
    @Description("Cycle")
    EventsCycles getEventsCycle() {
        return this.eventsCycles;
    }

    @Bean
    @Description("SmartHome")
    SmartHome getSmartHome() {
        return this.smartHome;
    }

}
