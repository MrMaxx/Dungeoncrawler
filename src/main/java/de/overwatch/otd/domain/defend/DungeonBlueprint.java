package de.overwatch.otd.domain.defend;

import de.overwatch.otd.domain.OtdEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.dungeon.complete",
                attributeNodes = {
                        @NamedAttributeNode("dungeonNodes"),
                        @NamedAttributeNode("constructionSites")
                }
        )
})
public class DungeonBlueprint  extends OtdEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "dungeonBlueprint")
    @Fetch(FetchMode.SUBSELECT)
    private Set<DungeonNode> dungeonNodes = new HashSet<DungeonNode>();

    @OneToMany(mappedBy = "dungeonBlueprint")
    @Fetch(FetchMode.SUBSELECT)
    private Set<ConstructionSite> constructionSites = new HashSet<ConstructionSite>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        if (!(o instanceof DungeonBlueprint)) return false;

        DungeonBlueprint that = (DungeonBlueprint) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "DungeonBlueprint{" +
                "name='" + name + '\'' +
                "id='" + id + '\'' +
                '}';
    }
}
