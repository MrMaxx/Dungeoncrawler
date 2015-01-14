package de.overwatch.otd.game.model;


public class Coordinate {

    /** in pixel */
    private int x;

    /** in pixel */
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
