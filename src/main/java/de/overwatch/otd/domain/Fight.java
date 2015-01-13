package de.overwatch.otd.domain;

import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.defend.Dungeon;

import javax.persistence.*;

/**
 * Represents an attack of a player on the Dungeon of another Player at a given time
 */
@Entity
public class Fight extends OtdEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dungeon_id", nullable = false)
    private Dungeon dungeon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attack_force_id", nullable = false)
    private AttackForce attackForce;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FightState fightState;

    @Column
    private String eventStream;

    public static enum FightState{
        ISSUED,
        COMPLETED,
        ERROR
    }

    public String getEventStream() {
        return eventStream;
    }

    public void setEventStream(String eventStream) {
        this.eventStream = eventStream;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public AttackForce getAttackForce() {
        return attackForce;
    }

    public void setAttackForce(AttackForce attackForce) {
        this.attackForce = attackForce;
    }

    public FightState getFightState() {
        return fightState;
    }

    public void setFightState(FightState fightState) {
        this.fightState = fightState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fight fight = (Fight) o;

        if (attackForce != null ? !attackForce.equals(fight.attackForce) : fight.attackForce != null) return false;
        if (dungeon != null ? !dungeon.equals(fight.dungeon) : fight.dungeon != null) return false;
        if (fightState != fight.fightState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dungeon != null ? dungeon.hashCode() : 0;
        result = 31 * result + (attackForce != null ? attackForce.hashCode() : 0);
        result = 31 * result + (fightState != null ? fightState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fight{" +
                "fightState=" + fightState +
                '}';
    }
}
