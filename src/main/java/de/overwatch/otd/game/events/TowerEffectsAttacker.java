package de.overwatch.otd.game.events;


public class TowerEffectsAttacker extends GameEvent {


    private int time;
    private Integer attackerId;
    private int effectWearsOffAt;

    public TowerEffectsAttacker(int elementId) {
        super(elementId);
    }


    @Override
    public String getType() {
        return EVENT_TYPE_TOWEREFFECTSATTACKER;
    }

    @Override
    public String toString() {
        return "TowerEffectsAttacker{" +
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

    public int getEffectWearsOffAt() {
        return effectWearsOffAt;
    }

    public void setEffectWearsOffAt(int effectWearsOffAt) {
        this.effectWearsOffAt = effectWearsOffAt;
    }
}
