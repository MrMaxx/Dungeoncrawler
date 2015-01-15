package de.overwatch.otd.domain.attack;


import com.fasterxml.jackson.annotation.JsonIgnore;
import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.*;

@Entity
public class Wave  extends OtdEntity {


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attack_force_id", nullable = false)
    private AttackForce attackForce;

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

        if (id != null ? !id.equals(wave.id) : wave.id != null) return false;
        if (attackerBlueprintId != null ? !attackerBlueprintId.equals(wave.attackerBlueprintId) : wave.attackerBlueprintId != null)
            return false;
        if (waveBlueprintId != null ? !waveBlueprintId.equals(wave.waveBlueprintId) : wave.waveBlueprintId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (attackerBlueprintId != null ? attackerBlueprintId.hashCode() : 0);
        result = 31 * result + (waveBlueprintId != null ? waveBlueprintId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wave{" +
                "id=" + id +
                ", attackerBlueprintId=" + attackerBlueprintId +
                ", waveBlueprintId=" + waveBlueprintId +
                '}';
    }
}
