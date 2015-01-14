package de.overwatch.otd.game.model;


public class Spawn {

    private int executeAfterMillis;
    private Attacker attacker;

    public Spawn(int executeAfterMillis, Attacker attacker) {
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
