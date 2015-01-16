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

    public Attacker(Integer id, AttackerBlueprint attackerBlueprint, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
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
}
