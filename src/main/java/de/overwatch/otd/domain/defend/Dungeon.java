package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;
import de.overwatch.otd.domain.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Entity
public class Dungeon  extends OtdEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer dungeonBlueprintId;

    @OneToMany(mappedBy = "dungeon")
    @Fetch(FetchMode.SUBSELECT)
    private Set<Tower> towers = new HashSet<Tower>();

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

    public Set<Tower> getTowers() {
        return towers;
    }

    public void setTowers(Set<Tower> towers) {
        this.towers = towers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dungeon dungeon = (Dungeon) o;

        if (id != null ? !id.equals(dungeon.id) : dungeon.id != null)
            return false;
        if (user != null ? !user.equals(dungeon.user) : dungeon.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
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
