package de.overwatch.otd.game.model;


public class AttackerSpawn {

    private int executeAfterMillis;
    private Attacker attacker;

    public AttackerSpawn(int executeAfterMillis, Attacker attacker) {
        this.executeAfterMillis = executeAfterMillis;
        this.attacker = attacker;
    }

    public Attacker getAttacker() {
        return attacker;
    }

    public int getExecuteAfterMillis() {

        return executeAfterMillis;
    }
}
