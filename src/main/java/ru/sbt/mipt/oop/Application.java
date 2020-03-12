package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.EventDoorHandler;
import ru.sbt.mipt.oop.handlers.EventHallDoorHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.EventLightHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeInitializer homeInitializer = new HomeJsonInitializer();
        SmartHome smartHome = homeInitializer.initializeHome();
        List<EventHandler> handlers = Arrays.asList(new EventDoorHandler(), new EventLightHandler(),
                new EventHallDoorHandler());
        // начинаем цикл обработки событий
        EventsCycle.runEvents(smartHome, handlers);
    }

}
