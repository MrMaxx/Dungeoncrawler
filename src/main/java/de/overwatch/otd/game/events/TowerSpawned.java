package de.overwatch.otd.game.events;


public class TowerSpawned extends GameEvent{

    private int time;
    private String type;
    private Integer id;
    private int x,y;

    @Override
    public String toString() {
        return "TowerSpawned{" +
                "time=" + time +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TowerSpawned)) return false;

        TowerSpawned that = (TowerSpawned) o;

        if (time != that.time) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time;
        result = 31 * result + (type != null ? type.hashCode() : 0);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
