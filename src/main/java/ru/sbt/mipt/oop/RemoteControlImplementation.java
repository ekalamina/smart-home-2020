package ru.sbt.mipt.oop;

import rc.RemoteControl;
import ru.sbt.mipt.oop.commands.Command;

import java.util.HashMap;
import java.util.Set;

public class RemoteControlImplementation implements RemoteControl {

    public HashMap<String, Command> commandHashMap;
    public String id;

    public RemoteControlImplementation(String id) {
        this.commandHashMap = new HashMap<>();
        this.id = id;
    }

    public void putCommand(String button, Command command) {
        if (!commandHashMap.containsKey(button)) {
            commandHashMap.put(button, command);
        }
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (id.equals(rcId) && commandHashMap.containsKey(buttonCode)) {
            commandHashMap.get(buttonCode).execute();
        }
    }


}
