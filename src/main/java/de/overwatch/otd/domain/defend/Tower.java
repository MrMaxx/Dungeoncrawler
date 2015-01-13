package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;

import javax.persistence.*;


@Entity
public class Tower extends OtdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dungeon_id", nullable = false)
    private Dungeon dungeon;

    @Column(nullable = false)
    private Integer constructionSiteId;

    @Column(nullable = false)
    private Integer towerBlueprintId;

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Integer getConstructionSiteId() {
        return constructionSiteId;
    }

    public void setConstructionSiteId(Integer constructionSiteId) {
        this.constructionSiteId = constructionSiteId;
    }

    public Integer getTowerBlueprintId() {
        return towerBlueprintId;
    }

    public void setTowerBlueprintId(Integer towerBlueprintId) {
        this.towerBlueprintId = towerBlueprintId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tower tower = (Tower) o;

        if (constructionSiteId != null ? !constructionSiteId.equals(tower.constructionSiteId) : tower.constructionSiteId != null)
            return false;
        if (dungeon != null ? !dungeon.equals(tower.dungeon) : tower.dungeon != null) return false;
        if (id != null ? !id.equals(tower.id) : tower.id != null) return false;
        if (towerBlueprintId != null ? !towerBlueprintId.equals(tower.towerBlueprintId) : tower.towerBlueprintId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dungeon != null ? dungeon.hashCode() : 0);
        result = 31 * result + (constructionSiteId != null ? constructionSiteId.hashCode() : 0);
        result = 31 * result + (towerBlueprintId != null ? towerBlueprintId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tower{" +
                "id=" + id +
                ", constructionSiteId=" + constructionSiteId +
                ", towerBlueprintId=" + towerBlueprintId +
                '}';
    }
}
