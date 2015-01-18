package de.overwatch.otd.game.events;


public class AttackerSpawned extends GameEvent{

    private int time;
    private String attackerType;
    private Integer id;
    private int x,y;

    @Override
    public String getType() {
        return EVENT_TYPE_ATTACKERSPAWN;
    }

    @Override
    public String toString() {
        return "AttackerSpawned{" +
                "time=" + time +
                ", attackerType='" + attackerType + '\'' +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                "} " + super.toString();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setAttackerType(String attackerType) {
        this.attackerType = attackerType;
    }

    public String getAttackerType() {
        return attackerType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
