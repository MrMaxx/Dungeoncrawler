package de.overwatch.otd.game.events;


public class TowerLostTarget extends GameEvent {


    private int time;
    private Integer attackerId;

    public TowerLostTarget(int elementId) {
        super(elementId);
    }


    @Override
    public String getType() {
        return EVENT_TYPE_TOWERLOSTTARGET;
    }

    @Override
    public String toString() {
        return "TowerLostTarget{" +
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
