package de.overwatch.otd.game.events;


public class AttackerSpawned extends GameEvent{

    private int time;
    private String type;
    private Integer id;

    @Override
    public String getType() {
        return EVENT_TYPE_SPAWN;
    }

    @Override
    public String toString() {
        return "AttackerSpawned{" +
                "time=" + time +
                ", type='" + type + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttackerSpawned that = (AttackerSpawned) o;

        if (time != that.time) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    public int getTime() {

        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
}
