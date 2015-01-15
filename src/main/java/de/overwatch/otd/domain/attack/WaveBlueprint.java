package de.overwatch.otd.domain.attack;


import com.fasterxml.jackson.annotation.JsonIgnore;
import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.*;

@Entity
public class WaveBlueprint  extends OtdEntity {

    @Column(nullable = false)
    private int dispatchesAfter;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attack_force_pattern_id", nullable = false)
    private AttackForcePattern attackForcePattern;

    @Column(nullable = false)
    private int slots;

    @Column(nullable = false)
    private int delayBetweenSpawns;

    public int getDelayBetweenSpawns() {
        return delayBetweenSpawns;
    }

    public void setDelayBetweenSpawns(int delayBetweenSpawns) {
        this.delayBetweenSpawns = delayBetweenSpawns;
    }

    public int getDispatchesAfter() {
        return dispatchesAfter;
    }

    public void setDispatchesAfter(int dispatchesAfter) {
        this.dispatchesAfter = dispatchesAfter;
    }

    public AttackForcePattern getAttackForcePattern() {
        return attackForcePattern;
    }

    public void setAttackForcePattern(AttackForcePattern attackForcePattern) {
        this.attackForcePattern = attackForcePattern;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WaveBlueprint that = (WaveBlueprint) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (delayBetweenSpawns != that.delayBetweenSpawns) return false;
        if (dispatchesAfter != that.dispatchesAfter) return false;
        if (slots != that.slots) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + dispatchesAfter;
        result = 31 * result + slots;
        result = 31 * result + delayBetweenSpawns;
        return result;
    }

    @Override
    public String toString() {
        return "WaveBlueprint{" +
                "id=" + id +
                ", dispatchesAfter=" + dispatchesAfter +
                ", slots=" + slots +
                ", delayBetweenSpawns=" + delayBetweenSpawns +
                '}';
    }
}
