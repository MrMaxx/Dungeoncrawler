package de.overwatch.otd.game.events;


public class TowerLostTarget extends GameEvent {


    private int time;
    private Integer attackerId;
    private Integer towerId;


    @Override
    public String getType() {
        return EVENT_TYPE_TOWERLOSTTARGET;
    }

    @Override
    public String toString() {
        return "TowerLostTarget{" +
                "attackerId=" + attackerId +
                ", towerId=" + towerId +
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

    public Integer getTowerId() {
        return towerId;
    }

    public void setTowerId(Integer towerId) {
        this.towerId = towerId;
    }
}
