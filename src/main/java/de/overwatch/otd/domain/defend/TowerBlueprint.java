package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class TowerBlueprint  extends OtdEntity {

    @Column(nullable = false)
    private String towerType;
    @Column(nullable = false)
    private int damage;
    @Column(nullable = false)
    private int timeToReload;
    @Column(nullable = false)
    private int attackRange;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TowerEffect towerEffect;

    @Column(nullable = false)
    private int slowsDownToPercent;

    @Column(nullable = false)
    private int effectWearsOffInMilliseconds;

    @Column(nullable = false)
    private int price;

    public static enum TowerEffect{
        SINGLE_DAMAGE,
        SINGLE_EFFECT
    }

    public TowerEffect getTowerEffect() {
        return towerEffect;
    }

    public void setTowerEffect(TowerEffect towerEffect) {
        this.towerEffect = towerEffect;
    }

    public int getEffectWearsOffInMilliseconds() {
        return effectWearsOffInMilliseconds;
    }

    public void setEffectWearsOffInMilliseconds(int effectWearsOffInMilliseconds) {
        this.effectWearsOffInMilliseconds = effectWearsOffInMilliseconds;
    }

    public int getSlowsDownToPercent() {
        return slowsDownToPercent;
    }

    public void setSlowsDownToPercent(int slowsDownToPercent) {
        this.slowsDownToPercent = slowsDownToPercent;
    }

    public String getType() {
        return towerType;
    }

    public void setType(String type) {
        this.towerType = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTimeToReload() {
        return timeToReload;
    }

    public void setTimeToReload(int timeToReload) {
        this.timeToReload = timeToReload;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int range) {
        this.attackRange = range;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TowerBlueprint that = (TowerBlueprint) o;

        if (damage != that.damage) return false;
        if (price != that.price) return false;
        if (attackRange != that.attackRange) return false;
        if (timeToReload != that.timeToReload) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (towerType != null ? !towerType.equals(that.towerType) : that.towerType != null) return false;
        if (towerEffect != that.towerEffect) return false;
        if (slowsDownToPercent != that.slowsDownToPercent) return false;
        if (effectWearsOffInMilliseconds != that.effectWearsOffInMilliseconds) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (towerType != null ? towerType.hashCode() : 0);
        result = 31 * result + damage;
        result = 31 * result + timeToReload;
        result = 31 * result + attackRange;
        result = 31 * result + price;
        result = 31 * result + (towerEffect != null ? towerEffect.hashCode() : 0);
        result = 31 * result + slowsDownToPercent;
        result = 31 * result + effectWearsOffInMilliseconds;
        return result;
    }

    @Override
    public String toString() {
        return "TowerBlueprint{" +
                "id=" + id +
                ", towerType='" + towerType + '\'' +
                ", damage=" + damage +
                ", timeToReload=" + timeToReload +
                ", attackRange=" + attackRange +
                ", price=" + price +
                ", towerEffect=" + towerEffect +
                ", slowdownInPercent=" + slowsDownToPercent +
                ", effectWearsOffInMilliseconds=" + effectWearsOffInMilliseconds +
                '}';
    }
}
