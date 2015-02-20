package de.overwatch.otd.game.events;


import de.overwatch.otd.game.model.Coordinate;
import org.apache.log4j.Logger;

public class TowerTargetedAttacker extends GameEvent {


    private int time;
    private Integer attackerId;

    public TowerTargetedAttacker(int elementId) {
        super(elementId);
    }


    @Override
    public String getType() {
        return EVENT_TYPE_TOWERTARGETSATTACKER;
    }

    @Override
    public String toString() {
        return "AttackerTargeted{" +
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
