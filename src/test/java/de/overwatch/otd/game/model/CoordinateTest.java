package de.overwatch.otd.game.model;


import org.junit.Assert;
import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testDistanceTo() throws Exception{

        Coordinate coordinate1 = new Coordinate(80, 80);
        Coordinate coordinate2 = new Coordinate(80, 120);

        double distance1 = coordinate1.getDistanceTo(coordinate2);
        double distance2 = coordinate2.getDistanceTo(coordinate1);

        Assert.assertEquals(distance1, distance2, 0);
        Assert.assertEquals(40, distance1, 0);

    }


}
