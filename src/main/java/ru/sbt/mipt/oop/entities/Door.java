package ru.sbt.mipt.oop.entities;

public class Door implements Actionable{
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean getIsOpen(){
        return isOpen;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}
