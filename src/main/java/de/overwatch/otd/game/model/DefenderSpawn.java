package de.overwatch.otd.game.model;


public class DefenderSpawn {

    private int executeAfterMillis;
    private Turret turret;

    public DefenderSpawn(int executeAfterMillis, Turret turret) {
        this.executeAfterMillis = executeAfterMillis;
        this.turret = turret;
    }

    public Turret getTurret() {
        return turret;
    }

    public int getExecuteAfterMillis() {

        return executeAfterMillis;
    }
}
