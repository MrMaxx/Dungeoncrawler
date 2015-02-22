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

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistanceTo(Coordinate target){

        double x1 = (this.getX()-target.getX())*(this.getX()-target.getX());
        double y1 = (this.getY()-target.getY())*(this.getY()-target.getY());
        return Math.sqrt(x1 + y1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
