package de.overwatch.otd.domain.attack;


import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.*;

@Entity
public class Wave  extends OtdEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attack_force_id", nullable = false)
    private AttackForce attackForce;

    // which is a copy of the corresponding waveblueprint
    @Column(nullable = false)
    private int dispatchesAfter;

    // which is a copy of the corresponding waveblueprint
    @Column(nullable = false)
    private int slots;

    @Column(nullable = false)
    private Integer attackerBlueprintId;

    @Column(nullable = false)
    private Integer waveBlueprintId;

    public AttackForce getAttackForce() {
        return attackForce;
    }

    public void setAttackForce(AttackForce attackForce) {
        this.attackForce = attackForce;
    }

    public int getDispatchesAfter() {
        return dispatchesAfter;
    }

    public void setDispatchesAfter(int dispatchesAfter) {
        this.dispatchesAfter = dispatchesAfter;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public Integer getAttackerBlueprintId() {
        return attackerBlueprintId;
    }

    public void setAttackerBlueprintId(Integer attackerBlueprintId) {
        this.attackerBlueprintId = attackerBlueprintId;
    }

    public Integer getWaveBlueprintId() {
        return waveBlueprintId;
    }

    public void setWaveBlueprintId(Integer waveBlueprintId) {
        this.waveBlueprintId = waveBlueprintId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wave wave = (Wave) o;

        if (dispatchesAfter != wave.dispatchesAfter) return false;
        if (slots != wave.slots) return false;
        if (attackForce != null ? !attackForce.equals(wave.attackForce) : wave.attackForce != null) return false;
        if (attackerBlueprintId != null ? !attackerBlueprintId.equals(wave.attackerBlueprintId) : wave.attackerBlueprintId != null)
            return false;
        if (id != null ? !id.equals(wave.id) : wave.id != null) return false;
        if (waveBlueprintId != null ? !waveBlueprintId.equals(wave.waveBlueprintId) : wave.waveBlueprintId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (attackForce != null ? attackForce.hashCode() : 0);
        result = 31 * result + dispatchesAfter;
        result = 31 * result + slots;
        result = 31 * result + (attackerBlueprintId != null ? attackerBlueprintId.hashCode() : 0);
        result = 31 * result + (waveBlueprintId != null ? waveBlueprintId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wave{" +
                "id=" + id +
                ", dispatchesAfter=" + dispatchesAfter +
                ", slots=" + slots +
                ", attackerBlueprintId=" + attackerBlueprintId +
                ", waveBlueprintId=" + waveBlueprintId +
                '}';
    }
}
