package de.overwatch.otd.domain.attack;


import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AttackerBlueprint  extends OtdEntity {

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int speed;
    @Column(nullable = false)
    private int maxHealth;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttackerBlueprint that = (AttackerBlueprint) o;

        if (maxHealth != that.maxHealth) return false;
        if (price != that.price) return false;
        if (speed != that.speed) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + speed;
        result = 31 * result + maxHealth;
        return result;
    }

    @Override
    public String toString() {
        return "AttackerBlueprint{" +
                "id='" + id + '\'' +
                "type='" + type + '\'' +
                ", price=" + price +
                ", speed=" + speed +
                ", maxHealth=" + maxHealth +
                '}';
    }
}
