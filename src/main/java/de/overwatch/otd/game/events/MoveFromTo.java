package de.overwatch.otd.game.events;


import de.overwatch.otd.game.model.Coordinate;

public class MoveFromTo extends GameEvent {


    private Integer attackerId;
    private int startsAt;
    private int endsAt;
    private Coordinate startingCoordinate;
    private Coordinate endingCoordinate;


    @Override
    public String getType() {
        return EVENT_TYPE_MOVETO;
    }

    @Override
    public String toString() {
        return "MoveFromTo{" +
                "attackerId=" + attackerId +
                ", startsAt=" + startsAt +
                ", endsAt=" + endsAt +
                ", startingCoordinate=" + startingCoordinate +
                ", endingCoordinate=" + endingCoordinate +
                "} " + super.toString();
    }

    public Integer getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Integer attackerId) {
        this.attackerId = attackerId;
    }

    public int getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(int startsAt) {
        this.startsAt = startsAt;
    }

    public int getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(int endsAt) {
        this.endsAt = endsAt;
    }

    public Coordinate getStartingCoordinate() {
        return startingCoordinate;
    }

    public void setStartingCoordinate(Coordinate startingCoordinate) {
        this.startingCoordinate = startingCoordinate;
    }

    public Coordinate getEndingCoordinate() {
        return endingCoordinate;
    }

    public void setEndingCoordinate(Coordinate endingCoordinate) {
        this.endingCoordinate = endingCoordinate;
    }
}
