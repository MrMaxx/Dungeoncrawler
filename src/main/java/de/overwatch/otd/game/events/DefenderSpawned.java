package de.overwatch.otd.game.events;


public class DefenderSpawned extends GameEvent{

    private int time;
    private String towerType;
    private Integer id;
    private int x,y;

    @Override
    public String getType() {
        return EVENT_TYPE_DEFENDERSPAWN;
    }

    @Override
    public String toString() {
        return "AttackerSpawned{" +
                "time=" + time +
                ", towerType='" + towerType + '\'' +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefenderSpawned)) return false;

        DefenderSpawned that = (DefenderSpawned) o;

        if (time != that.time) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (towerType != null ? !towerType.equals(that.towerType) : that.towerType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time;
        result = 31 * result + (towerType != null ? towerType.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTowerType() {
        return towerType;
    }

    public void setTowerType(String towerType) {
        this.towerType = towerType;
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
