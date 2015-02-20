package de.overwatch.otd.game.events;


public class AttackerDied extends GameEvent {


    private int time;

    public AttackerDied(int elementId) {
        super(elementId);
    }

    @Override
    public String getType() {
        return EVENT_TYPE_ATTACKERDIED;
    }

    @Override
    public String toString() {
        return "AttackerDied{" +
                ", time=" + time +
                "} " + super.toString();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
