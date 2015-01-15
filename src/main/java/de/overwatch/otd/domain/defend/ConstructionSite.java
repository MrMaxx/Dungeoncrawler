package de.overwatch.otd.domain.defend;


import com.fasterxml.jackson.annotation.JsonIgnore;
import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.*;

@Entity
public class ConstructionSite extends OtdEntity{

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dungeon_blueprint_id", nullable = false)
    private DungeonBlueprint dungeonBlueprint;

    @Column
    private int x;

    @Column
    private int y;

    public DungeonBlueprint getDungeonBlueprint() {
        return dungeonBlueprint;
    }

    public void setDungeonBlueprint(DungeonBlueprint dungeonBlueprint) {
        this.dungeonBlueprint = dungeonBlueprint;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstructionSite that = (ConstructionSite) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "ConstructionSite{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
