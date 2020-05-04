package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.decorators.SmsDecorator;
import ru.sbt.mipt.oop.handlers.*;

import java.util.Arrays;
import java.util.List;

public class Application {

    private final static SensorCommandSender sensorCommandSender = new SensorCommandSender();

    private final static List<EventHandler> handlers = Arrays.asList(new EventAlarmHandler(),
            new SmsDecorator(Arrays.asList(new EventDoorHandler(),
                    new EventLightHandler(),
                    new EventHallDoorHandler(sensorCommandSender))));


    public static void main(String... args) {
        // считываем состояние дома из файла
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        // начинаем цикл обработки событий
        EventsCycles eventsCycles = new EventsCycle(handlers);
        eventsCycles.runCycle(smartHome);
    }

}
