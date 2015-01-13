package de.overwatch.otd.domain.attack;



import de.overwatch.otd.domain.OtdEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class AttackForcePattern  extends OtdEntity {

    @Column(nullable = false)
    private String patternName;

    @OneToMany(mappedBy = "attackForcePattern")
    @OrderBy("dispatchesAfter ASC")
    @Fetch(FetchMode.SUBSELECT)
    private List<WaveBlueprint> waves;

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public List<WaveBlueprint> getWaves() {
        return waves;
    }

    public void setWaves(List<WaveBlueprint> waves) {
        this.waves = waves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttackForcePattern that = (AttackForcePattern) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!patternName.equals(that.patternName)) return false;
        if (waves != null ? !waves.equals(that.waves) : that.waves != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + patternName.hashCode();
        result = 31 * result + (waves != null ? waves.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttackForcePattern{" +
                "patternName='" + patternName + '\'' +
                ", id=" + id +
                '}';
    }
}
