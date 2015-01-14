package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DungeonBlueprint  extends OtdEntity {

    @OneToMany(mappedBy = "dungeonBlueprint")
    @Fetch(FetchMode.SUBSELECT)
    private Set<DungeonNode> dungeonNodes = new HashSet<DungeonNode>();

    @OneToMany(mappedBy = "dungeonBlueprint")
    @Fetch(FetchMode.SUBSELECT)
    private Set<ConstructionSite> constructionSites = new HashSet<ConstructionSite>();

    public Set<DungeonNode> getDungeonNodes() {
        return dungeonNodes;
    }

    public void setDungeonNodes(Set<DungeonNode> dungeonNodes) {
        this.dungeonNodes = dungeonNodes;
    }

    public Set<ConstructionSite> getConstructionSites() {
        return constructionSites;
    }

    public void setConstructionSites(Set<ConstructionSite> constructionSites) {
        this.constructionSites = constructionSites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DungeonBlueprint that = (DungeonBlueprint) o;

        if (constructionSites != null ? !constructionSites.equals(that.constructionSites) : that.constructionSites != null)
            return false;
        if (dungeonNodes != null ? !dungeonNodes.equals(that.dungeonNodes) : that.dungeonNodes != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dungeonNodes != null ? dungeonNodes.hashCode() : 0);
        result = 31 * result + (constructionSites != null ? constructionSites.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DungeonBlueprint{" +
                "id=" + id +
                '}';
    }
}
