package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;
import de.overwatch.otd.domain.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Dungeon  extends OtdEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer dungeonBlueprintId;

    @OneToMany(mappedBy = "dungeon")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Tower> towers;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDungeonBlueprintId() {
        return dungeonBlueprintId;
    }

    public void setDungeonBlueprintId(Integer dungeonBlueprintId) {
        this.dungeonBlueprintId = dungeonBlueprintId;
    }

    public Collection<Tower> getTowers() {
        return towers;
    }

    public void setTowers(Collection<Tower> towers) {
        this.towers = towers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dungeon dungeon = (Dungeon) o;

        if (dungeonBlueprintId != null ? !dungeonBlueprintId.equals(dungeon.dungeonBlueprintId) : dungeon.dungeonBlueprintId != null)
            return false;
        if (towers != null ? !towers.equals(dungeon.towers) : dungeon.towers != null) return false;
        if (user != null ? !user.equals(dungeon.user) : dungeon.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (dungeonBlueprintId != null ? dungeonBlueprintId.hashCode() : 0);
        result = 31 * result + (towers != null ? towers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "dungeonBlueprintId=" + dungeonBlueprintId +
                '}';
    }
}
