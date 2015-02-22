package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.GameTest;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Coordinate;
import de.overwatch.otd.game.model.NodeVisit;
import de.overwatch.otd.game.model.Turret;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SingleEffectTargetingProcessorTest extends GameTest{

    GameState gameState;
    Turret turret;
    Attacker attacker;

    @Before
    public void init(){
        gameState = defaultGameState();
    }

    @Test
    public void testHit() throws Exception{
        int tick = 100;

        // our tick is 100ms...attacker started at 80.10, with speed 100 its at 80,110 by now
        Coordinate currentCoordinate = new Coordinate(80,110);

        turret = createTurret(1, effectTowerBlueprint(), createConstructionSite(1, 80, 80));
        attacker = createAttacker(1, defaultAttackerBlueprint(), currentCoordinate, new NodeVisit(new Coordinate(80,10), 0));

        Coordinate next = new Coordinate(80,210);
        attacker.setNextNodeVisit(
                new NodeVisit(
                        next,
                        200));

        gameState.getAttackers().add(attacker);
        gameState.getTurrets().add(turret);

        int originalSpeed = attacker.getSpeed();
        int assertNewSpeed = (int)(originalSpeed / 100.0 * turret.getSlowsDownToPercent());

        Assert.assertTrue(attacker.hasNoEffect());
        Assert.assertEquals(1, attacker.getNodeVisits().size());

        // FIRST the Effect Tower hits the attacker
        SingleEffectTowerTargetingProcessor.process(gameState, tick);

        // Attacker now has the reduced Speed
        Assert.assertEquals(assertNewSpeed, attacker.getSpeed());
        Assert.assertFalse(attacker.hasNoEffect());

        // The Effect Event has been generated
        Assert.assertEquals(1, gameState.getTickToEventsMap().get(tick).size());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWEREFFECTSATTACKER, gameState.getTickToEventsMap().get(tick).get(0).getType());

        // The intermediate NodeVisit has been generated
        Assert.assertEquals(2, attacker.getNodeVisits().size());
        Assert.assertEquals(attacker.getLastNodeVisit(), attacker.getNodeVisits().get(1));
        Assert.assertEquals(tick, attacker.getLastNodeVisit().getVisitTime());
        Assert.assertEquals(currentCoordinate, attacker.getLastNodeVisit().getCoordinate());

        // the NextNodeVisits visitTime has been corrected
        Assert.assertEquals(
                tick + attacker.getLastNodeVisit().getTransitTimeInMillis(attacker.getNextNodeVisit().getCoordinate(), attacker.getSpeed()),
                attacker.getNextNodeVisit().getVisitTime());

        // SECOND the Effect is being refreshed (Turrets reloarTime is smaller than EffectWearsOf in this case)
        // we set the attackers coordinate manually for comparison
        attacker.moveTo(new Coordinate(80,160));
        int secondTick = attacker.getEffectWearsOffAt();

        SingleEffectTowerTargetingProcessor.process(gameState, secondTick);

        // a new Effect Event has been generated
        Assert.assertEquals(1, gameState.getTickToEventsMap().get(secondTick).size());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWEREFFECTSATTACKER, gameState.getTickToEventsMap().get(secondTick).get(0).getType());

        // the Effect has been lengthened
        Assert.assertEquals(secondTick+turret.getEffectWearsOffInMilliseconds(), attacker.getEffectWearsOffAt());

        // the NodeVisits of the attacker are untouched
        Assert.assertEquals(2, attacker.getNodeVisits().size());

        // The attacker is still under the effect
        Assert.assertFalse(attacker.hasNoEffect());

        // THIRD the Effect wears off
        // we prevent the turret from firing again by setting its lastShot into the far far future
        turret.setLastShot(999999);

        Coordinate effectWearsOffCoordinate = new Coordinate(80, 180);
        attacker.moveTo(effectWearsOffCoordinate);
        int thirdTick = attacker.getEffectWearsOffAt();

        SingleEffectTowerTargetingProcessor.process(gameState, thirdTick);

        // The Effect wore off
        Assert.assertTrue(attacker.hasNoEffect());

        // a new Intermediate NodeVisit has been created
        Assert.assertEquals(3, attacker.getNodeVisits().size());
        Assert.assertEquals(attacker.getLastNodeVisit(), attacker.getNodeVisits().get(2));
        Assert.assertEquals(thirdTick, attacker.getLastNodeVisit().getVisitTime());
        Assert.assertEquals(effectWearsOffCoordinate, attacker.getLastNodeVisit().getCoordinate());

        // attacker got back its old Speed
        Assert.assertEquals(originalSpeed, attacker.getSpeed());

    }


}
