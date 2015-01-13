package de.overwatch.otd.domain.attack;


import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.*;

@Entity
public class WaveBlueprint  extends OtdEntity {

    @Column(nullable = false)
    private int dispatchesAfter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attack_force_pattern_id", nullable = false)
    private AttackForcePattern attackForcePattern;

    @Column(nullable = false)
    private int slots;

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

        if (dispatchesAfter != that.dispatchesAfter) return false;
        if (slots != that.slots) return false;
        if (attackForcePattern != null ? !attackForcePattern.equals(that.attackForcePattern) : that.attackForcePattern != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + dispatchesAfter;
        result = 31 * result + (attackForcePattern != null ? attackForcePattern.hashCode() : 0);
        result = 31 * result + slots;
        return result;
    }

    @Override
    public String toString() {
        return "WaveBlueprint{" +
                "id=" + id +
                ", dispatchesAfter=" + dispatchesAfter +
                ", slots=" + slots +
                '}';
    }
}
