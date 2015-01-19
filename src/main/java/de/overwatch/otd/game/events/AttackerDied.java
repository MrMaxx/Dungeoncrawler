package de.overwatch.otd.game.events;


public class AttackerDied extends GameEvent {


    private int time;
    private Integer attackerId;


    @Override
    public String getType() {
        return EVENT_TYPE_ATTACKERDIED;
    }

    @Override
    public String toString() {
        return "AttackerDied{" +
                "attackerId=" + attackerId +
                ", time=" + time +
                "} " + super.toString();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Integer getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Integer attackerId) {
        this.attackerId = attackerId;
    }
}
