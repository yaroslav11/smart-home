package ru.sbt.mipt.oop;

public class SecuredSmartHome {
    public SmartHome smartHome;
    public AlarmSystem alarmSystem;

    public SecuredSmartHome(SmartHome smartHome, AlarmSystem alarmSystem){
        this.smartHome = smartHome;
        this.alarmSystem = alarmSystem;
    }
}
