package de.overwatch.otd.game.model;

import org.junit.Assert;
import org.junit.Test;


public class NodeVisitTest {


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNodeVisitTime() throws Exception{
        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 1000);
        NodeVisit nodeVisit2 = new NodeVisit(new Coordinate(1000,2000), 1000);

        nodeVisit.getCurrentTransitCoordinate(nodeVisit2, 10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalTickTime() throws Exception{
        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 1000);
        NodeVisit nodeVisit2 = new NodeVisit(new Coordinate(1000,2000), 2000);

        nodeVisit.getCurrentTransitCoordinate(nodeVisit2, 500);
    }

    @Test
    public void testMovingSouth() throws Exception{
        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 0);
        NodeVisit nodeVisit2 = new NodeVisit(new Coordinate(1000,2000), 1000);

        Coordinate coordinate = nodeVisit.getCurrentTransitCoordinate(nodeVisit2, 100);

        Assert.assertEquals(1000, coordinate.getX());
        Assert.assertEquals(1100, coordinate.getY());
    }


    @Test
    public void testMovingNorth() throws Exception{
        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 0);
        NodeVisit nodeVisit2 = new NodeVisit(new Coordinate(1000,0), 1000);

        Coordinate coordinate = nodeVisit.getCurrentTransitCoordinate(nodeVisit2, 100);

        Assert.assertEquals(1000, coordinate.getX());
        Assert.assertEquals(900, coordinate.getY());
    }

    @Test
    public void testMovingWest() throws Exception{
        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 0);
        NodeVisit nodeVisit2 = new NodeVisit(new Coordinate(00,1000), 1000);

        Coordinate coordinate = nodeVisit.getCurrentTransitCoordinate(nodeVisit2, 100);

        Assert.assertEquals(900, coordinate.getX());
        Assert.assertEquals(1000, coordinate.getY());
    }

    @Test
    public void testMovingEast() throws Exception{
        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 0);
        NodeVisit nodeVisit2 = new NodeVisit(new Coordinate(2000,1000), 1000);

        Coordinate coordinate = nodeVisit.getCurrentTransitCoordinate(nodeVisit2, 100);

        Assert.assertEquals(1100, coordinate.getX());
        Assert.assertEquals(1000, coordinate.getY());
    }


    @Test
    public void testTransitionTime() throws Exception{

        NodeVisit nodeVisit = new NodeVisit(new Coordinate(1000,1000), 0);

        // NORTH
        Assert.assertEquals(10000, nodeVisit.getTransitTimeInMillis(new Coordinate(1000,0), 100));

        // SOUTH
        Assert.assertEquals(10000, nodeVisit.getTransitTimeInMillis(new Coordinate(1000,2000), 100));

        // EAST
        Assert.assertEquals(10000, nodeVisit.getTransitTimeInMillis(new Coordinate(2000,1000), 100));

        // WEST
        Assert.assertEquals(10000, nodeVisit.getTransitTimeInMillis(new Coordinate(0,1000), 100));
    }

    @Test
    public void test() throws Exception{

        NodeVisit lastVisit = new NodeVisit(new Coordinate(880,640),50000);

        int transitTime = lastVisit.getTransitTimeInMillis(new Coordinate(880,120), 15);

        Assert.assertEquals(34666, transitTime);
    }

}
