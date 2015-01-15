package de.overwatch.otd.domain.attack;



import de.overwatch.otd.domain.OtdEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.attackForcePattern.complete",
                attributeNodes = {
                        @NamedAttributeNode("waveBlueprints")
                }
        )
})
@Entity
public class AttackForcePattern  extends OtdEntity {

    @Column(nullable = false)
    private String patternName;

    @OneToMany(mappedBy = "attackForcePattern", fetch = FetchType.EAGER)
    @OrderBy("dispatchesAfter ASC")
    private List<WaveBlueprint> waveBlueprints = new LinkedList<WaveBlueprint>();

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public List<WaveBlueprint> getWaveBlueprints() {
        return waveBlueprints;
    }

    public void setWaveBlueprints(List<WaveBlueprint> waveBlueprints) {
        this.waveBlueprints = waveBlueprints;
    }

    @Override
    public String toString() {
        return "AttackForcePattern{" +
                "id='" + id + '\'' +
                ", patternName='" + patternName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttackForcePattern that = (AttackForcePattern) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (patternName != null ? !patternName.equals(that.patternName) : that.patternName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patternName != null ? patternName.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
