package de.overwatch.otd.game.model;

import de.overwatch.otd.domain.attack.AttackerBlueprint;

import java.util.LinkedList;
import java.util.List;


public class Attacker {

    /** abstract id unique only per Fight */
    private Integer id;
    /** type from AttackerBlueprint */
    private String type;

    /** type from AttackerBlueprint */
    private int price;

    /** in pixel per second */
    private int speed;

    private int health;

    private Coordinate coordinate;
    private NodeVisit lastNodeVisit;
    private int lastDungeonNodeIndex;
    private NodeVisit nextNodeVisit;

    private List<Turret> beingTargetedBy = new LinkedList<Turret>();

    public Attacker(Integer id, AttackerBlueprint attackerBlueprint) {
        this.id = id;
        this.type = attackerBlueprint.getType();
        this.speed = attackerBlueprint.getSpeed();
        this.health = attackerBlueprint.getMaxHealth();
        this.price = attackerBlueprint.getPrice();
    }

    public void moveTo(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public void inflictDamage (int damage){
        this.health = health - damage;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public void setLastDungeonNodeVisit(NodeVisit lastDungeonNodeVisit, int dungeonNodeIndex ) {
        this.lastNodeVisit = lastDungeonNodeVisit;
        this.lastDungeonNodeIndex = dungeonNodeIndex;
    }

    public void setNextNodeVisit(NodeVisit nextNodeVisit) {
        this.nextNodeVisit = nextNodeVisit;
    }


    public int getPrice() {
        return price;
    }

    public List<Turret> getBeingTargetedBy() {
        return beingTargetedBy;
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

    /** The id of an Attacker can never be null...plus its unique for one Fight. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attacker)) return false;

        Attacker attacker = (Attacker) o;

        if (!id.equals(attacker.id)) return false;

        return true;
    }

    /** The id of an Attacker can never be null...plus its unique for one Fight. */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
