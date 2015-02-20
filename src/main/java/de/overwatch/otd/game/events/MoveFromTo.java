package de.overwatch.otd.game.events;


import de.overwatch.otd.game.model.Coordinate;

public class MoveFromTo extends GameEvent {

    private int time;
    private int endsAt;
    private Coordinate startingCoordinate;
    private Coordinate endingCoordinate;

    public MoveFromTo(int elementId) {
        super(elementId);
    }


    @Override
    public String getType() {
        return EVENT_TYPE_MOVETO;
    }

    @Override
    public String toString() {
        return "MoveFromTo{" +
                ", time=" + time +
                ", endsAt=" + endsAt +
                ", startingCoordinate=" + startingCoordinate +
                ", endingCoordinate=" + endingCoordinate +
                "} " + super.toString();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
