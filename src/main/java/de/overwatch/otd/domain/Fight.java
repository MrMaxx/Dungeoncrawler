package de.overwatch.otd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.overwatch.otd.domain.attack.AttackForce;
import de.overwatch.otd.domain.defend.Dungeon;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents an attack of a player on the Dungeon of another Player at a given time
 */
@Entity
public class Fight extends OtdEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dungeon_id", nullable = false)
    private Dungeon dungeon;

    @Column
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attack_force_id", nullable = false)
    private AttackForce attackForce;

    /* redundant...fight is used too often as to have so much joins */
    @Column(nullable = false)
    private Integer attackerId;
    @Column(nullable = false)
    private Integer defenderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FightState fightState;

    @Enumerated(EnumType.STRING)
    @Column
    private Outcome outcome;

    @JsonIgnore
    @Column
    @Lob
    private String events;

    public static enum FightState{
        ISSUED,
        COMPLETED,
        ERROR
    }

    public static enum Outcome{
        ATTACKER_WON,
        DEFENDER_WON,
        DRAW
    }

    public Integer getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Integer attackerId) {
        this.attackerId = attackerId;
    }

    public Integer getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(Integer defenderId) {
        this.defenderId = defenderId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
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

        if (id != null ? !id.equals(fight.id) : fight.id != null) return false;
        if (attackForce != null ? !attackForce.equals(fight.attackForce) : fight.attackForce != null) return false;
        if (dungeon != null ? !dungeon.equals(fight.dungeon) : fight.dungeon != null) return false;
        if (fightState != fight.fightState) return false;
        if (outcome != fight.outcome) return false;
        if (attackerId != fight.attackerId) return false;
        if (defenderId != fight.defenderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dungeon != null ? dungeon.hashCode() : 0;
        result = 31 * result + (attackForce != null ? attackForce.hashCode() : 0);
        result = 31 * result + (fightState != null ? fightState.hashCode() : 0);
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + (attackerId != null ? attackerId.hashCode() : 0);
        result = 31 * result + (defenderId != null ? defenderId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fight{" +
                "id=" + id +
                ", fightState='" + fightState + '\'' +
                ", outcome='" + outcome + '\'' +
                ", created='" + created + '\'' +
                ", attackerId='" + attackerId + '\'' +
                ", defenderId='" + defenderId + '\'' +
                '}';
    }
}
