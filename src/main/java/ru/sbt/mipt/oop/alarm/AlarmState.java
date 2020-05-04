package ru.sbt.mipt.oop.alarm;

public interface AlarmState {
    void alert();

    void activateAlarm(String code);

    void deactivateAlarm(String code);
}
