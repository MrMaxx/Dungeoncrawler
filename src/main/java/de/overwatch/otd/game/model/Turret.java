package de.overwatch.otd.game.model;


import de.overwatch.otd.domain.defend.ConstructionSite;
import de.overwatch.otd.domain.defend.TowerBlueprint;

public class Turret {

    /** abstract id unique only per Fight */
    private Integer id;
    /** type from AttackerBlueprint */
    private String type;

    private Coordinate coordinate;

    private int damage;
    private int timeToReload;
    private int range;

    private TowerBlueprint.TowerEffect towerEffect;

    private int slowsDownToPercent;

    private int effectWearsOffInMilliseconds;

    private Attacker currentTarget;
    private int lastShot = 0;

    public Turret(Integer id, TowerBlueprint towerBlueprint, ConstructionSite constructionSite) {
        this.id = id;
        this.type = towerBlueprint.getType();
        this.coordinate = new Coordinate(constructionSite.getX(), constructionSite.getY());
        this.damage = towerBlueprint.getDamage();
        this.timeToReload = towerBlueprint.getTimeToReload();
        this.range = towerBlueprint.getAttackRange();
        this.towerEffect = towerBlueprint.getTowerEffect();
        this.slowsDownToPercent = towerBlueprint.getSlowsDownToPercent();
        this.effectWearsOffInMilliseconds = towerBlueprint.getEffectWearsOffInMilliseconds();
    }


    public int getLastShot() {
        return lastShot;
    }

    public void setLastShot(int lastShot) {
        this.lastShot = lastShot;
    }

    public TowerBlueprint.TowerEffect getTowerEffect() {
        return towerEffect;
    }

    public int getEffectWearsOffInMilliseconds() {
        return effectWearsOffInMilliseconds;
    }

    public int getSlowsDownToPercent() {
        return slowsDownToPercent;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getDamage() {
        return damage;
    }

    public int getTimeToReload() {
        return timeToReload;
    }

    public int getRange() {
        return range;
    }

    public Attacker getCurrentTarget() {
        return currentTarget;
    }

    public void setCurrentTarget(Attacker currentTarget) {
        this.currentTarget = currentTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turret)) return false;

        Turret turret = (Turret) o;

        if (id != null ? !id.equals(turret.id) : turret.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
