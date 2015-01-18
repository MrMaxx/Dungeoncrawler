package de.overwatch.otd.game.model;

/**
 * NodeVisit is a helper on computing current coordinates for Attackers
 */
public class NodeVisit {

    private Coordinate coordinate;
    private int visitTime;

    public NodeVisit(Coordinate coordinate, int visitTime) {
        this.coordinate = coordinate;
        this.visitTime = visitTime;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getVisitTime() {
        return visitTime;
    }


    @Override
    public String toString() {
        return "NodeVisit{" +
                "coordinate=" + coordinate +
                ", visitTime=" + visitTime +
                '}';
    }

    public Coordinate getCurrentTransitCoordinate(NodeVisit nodeVisit, int currentTimeInMillis){

        if( this.visitTime >= currentTimeInMillis){
            throw new IllegalArgumentException("Targets NodeVisit.visitTime has to be bigger than this.visitTime.(this.visitTime, currentTimeInMillis) = ("+visitTime+", "+currentTimeInMillis+")");
        }

        if( this.visitTime >= nodeVisit.visitTime){
            throw new IllegalArgumentException("Targets NodeVisit.visitTime has to be bigger than this.visitTime.(this.visitTime, nodeVisit.visitTime) = ("+visitTime+", "+nodeVisit.visitTime+")");
        }

        Direction direction = Direction.getDirection(this.coordinate, nodeVisit.coordinate);

        int timeDelta = currentTimeInMillis - this.visitTime ;

        int distance = Math.abs((this.getCoordinate().getX() - nodeVisit.getCoordinate().getX()) +
                Math.abs(this.getCoordinate().getY() - nodeVisit.getCoordinate().getY()));
        int totalTimeDelta = nodeVisit.getVisitTime() - this.visitTime;

        float pixelsPerMillisecond = distance / (float)totalTimeDelta;

        return ( new Coordinate(
                this.coordinate.getX() + ( direction.getxModifier() *  (int)(timeDelta * pixelsPerMillisecond)),
                this.coordinate.getY() + ( direction.getyModifier() *  (int)(timeDelta * pixelsPerMillisecond))));

    }

    public int getTransitTimeInMillis(Coordinate coordinate, int speedInPixelsPerSeconds){
        if( this.getCoordinate().getX() != coordinate.getX() &&  this.getCoordinate().getY() != coordinate.getY()){
            throw new IllegalArgumentException("We only allow Coordinates that face each other in a 90 degree angle.");
        }
        if( this.getCoordinate().getX() == coordinate.getX() &&  this.getCoordinate().getY() == coordinate.getY()){
            throw new IllegalArgumentException("There is no Direction if source and target are the same");
        }
        int distance = Math.abs((this.getCoordinate().getX() - coordinate.getX()) +
                (this.getCoordinate().getY() - coordinate.getY()));

        return (int)Math.floor((distance / (float)speedInPixelsPerSeconds) * 1000);
    }

    private enum Direction {

        NORTH(0,-1),
        EAST(1,0),
        WEST(-1,0),
        SOUTH(0,1);

        private int xModifier, yModifier;

        private Direction(int x, int y){
            this.xModifier = x;
            this.yModifier = y;
        }

        public int getxModifier() {
            return xModifier;
        }

        public int getyModifier() {
            return yModifier;
        }

        /**
         * Directions are being conputed using the axis of a canvas (y-axis switched)
         */
        public static Direction getDirection(Coordinate source, Coordinate target){
            if( source.getX() != target.getX() &&  source.getY() != target.getY()){
                throw new IllegalArgumentException("We only allow Coordinates that face each other in a 90 degree angle.");
            }
            if( source.getX() == target.getX() &&  source.getY() == target.getY()){
                throw new IllegalArgumentException("There is no Direction if source and target are the same");
            }

            if( source.getX() == target.getX() && (target.getY() - source.getY() > 0)){
                return SOUTH;
            }else if ( source.getX() == target.getX() && (target.getY() - source.getY() < 0) ){
                return NORTH;
            }else if ( source.getY() == target.getY() && (target.getX() - source.getX() > 0) ){
                return EAST;
            }else {  // if ( source.getY() == target.getY() && (target.getX() - source.getX() < 0) ){
                return WEST;
            }
        }


    }

}
