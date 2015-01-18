package de.overwatch.otd.game.model;


import de.overwatch.otd.domain.attack.AttackerBlueprint;
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

    public Turret(Integer id, TowerBlueprint towerBlueprint, ConstructionSite constructionSite) {
        this.type = towerBlueprint.getType();
        this.coordinate = new Coordinate(constructionSite.getX(), constructionSite.getY());
        this.damage = towerBlueprint.getDamage();
        this.timeToReload = towerBlueprint.getTimeToReload();
        this.range = towerBlueprint.getRange();
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
}
