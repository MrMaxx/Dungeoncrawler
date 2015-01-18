package de.overwatch.otd.game.model;

import de.overwatch.otd.domain.attack.AttackerBlueprint;


public class Attacker {

    /** abstract id unique only per Fight */
    private Integer id;
    /** type from AttackerBlueprint */
    private String type;

    /** in pixel per second */
    private int speed;

    private int health;

    private Coordinate coordinate;
    private NodeVisit lastNodeVisit;
    private int lastDungeonNodeIndex;
    private NodeVisit nextNodeVisit;


    public Attacker(Integer id, AttackerBlueprint attackerBlueprint) {
        this.id = id;
        type = attackerBlueprint.getType();
        speed = attackerBlueprint.getSpeed();
        health = attackerBlueprint.getMaxHealth();
    }

    public void moveTo(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public void inflictDamage (int damage){
        health -= damage;
    }

    public boolean isDead(){
        return health > 0;
    }

    public void setLastDungeonNodeVisit(NodeVisit lastDungeonNodeVisit, int dungeonNodeIndex ) {
        this.lastNodeVisit = lastDungeonNodeVisit;
        this.lastDungeonNodeIndex = dungeonNodeIndex;
    }

    public void setNextNodeVisit(NodeVisit nextNodeVisit) {
        this.nextNodeVisit = nextNodeVisit;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public NodeVisit getLastNodeVisit() {
        return lastNodeVisit;
    }

    public int getLastDungeonNodeIndex() {
        return lastDungeonNodeIndex;
    }

    public NodeVisit getNextNodeVisit() {
        return nextNodeVisit;
    }

    @Override
    public String toString() {
        return "Attacker{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", speed=" + speed +
                ", health=" + health +
                ", coordinate=" + coordinate +
                ", lastNodeVisit=" + lastNodeVisit +
                ", lastDungeonNodeIndex=" + lastDungeonNodeIndex +
                ", nextNodeVisit=" + nextNodeVisit +
                '}';
    }
}
