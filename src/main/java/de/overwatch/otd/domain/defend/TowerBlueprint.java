package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TowerBlueprint  extends OtdEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int damage;
    @Column(nullable = false)
    private int timeToReload;
    @Column(nullable = false)
    private int range;
    @Column(nullable = false)
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
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
        if (range != that.range) return false;
        if (timeToReload != that.timeToReload) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + damage;
        result = 31 * result + timeToReload;
        result = 31 * result + range;
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "TowerBlueprint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", timeToReload=" + timeToReload +
                ", range=" + range +
                ", price=" + price +
                '}';
    }
}
