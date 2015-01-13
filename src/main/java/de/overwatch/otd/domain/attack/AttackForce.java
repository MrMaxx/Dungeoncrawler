package de.overwatch.otd.domain.attack;


import de.overwatch.otd.domain.OtdEntity;
import de.overwatch.otd.domain.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class AttackForce  extends OtdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer attackForcePatternId;

    @OneToMany(mappedBy = "attackForce")
    @OrderBy("dispatchesAfter ASC")
    @Fetch(FetchMode.SUBSELECT)
    private List<Wave> waves;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAttackForcePatternId() {
        return attackForcePatternId;
    }

    public void setAttackForcePatternId(Integer attackForcePatternId) {
        this.attackForcePatternId = attackForcePatternId;
    }

    public List<Wave> getWaves() {
        return waves;
    }

    public void setWaves(List<Wave> waves) {
        this.waves = waves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttackForce that = (AttackForce) o;

        if (attackForcePatternId != null ? !attackForcePatternId.equals(that.attackForcePatternId) : that.attackForcePatternId != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (waves != null ? !waves.equals(that.waves) : that.waves != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (attackForcePatternId != null ? attackForcePatternId.hashCode() : 0);
        result = 31 * result + (waves != null ? waves.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AttackForce{" +
                "id=" + id +
                ", attackForcePatternId=" + attackForcePatternId +
                '}';
    }
}
