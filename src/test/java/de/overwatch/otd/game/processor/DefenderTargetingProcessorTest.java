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
import org.mockito.Mockito;


public class DefenderTargetingProcessorTest extends GameTest{

    GameState gameState;
    Turret turret;
    Attacker attacker;

    @Before
    public void init(){
        gameState = defaultGameState();

        turret = createTurret(1, defaultTowerBlueprint(), createConstructionSite(1, 80, 80));

        gameState.getTurrets().add(turret);
    }

    @Test
    public void testFirstHit() throws Exception{
        attacker = createAttacker(1, defaultAttackerBlueprint(), new Coordinate(80,120), new NodeVisit(new Coordinate(80,120), 1000));
        gameState.getAttackers().add(attacker);

        int tick = 100 + turret.getTimeToReload();
        int oldHealth = attacker.getHealth();
        SingleDamageTargetingProcessor.process(gameState, tick);

        Assert.assertEquals(oldHealth - turret.getDamage(), attacker.getHealth());
        Assert.assertEquals(1, gameState.getTickToEventsMap().get(tick).size());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWERTARGETSATTACKER, gameState.getTickToEventsMap().get(tick).get(0).getType());
    }

    @Test
    public void testKilling() throws Exception{
        attacker = createAttacker(1, nearDeadAttackerBlueprint(), new Coordinate(80,120), new NodeVisit(new Coordinate(80,120), 1000));
        gameState.getAttackers().add(attacker);

        int tick = 100 + turret.getTimeToReload();
        SingleDamageTargetingProcessor.process(gameState, tick);

        Assert.assertTrue(attacker.isDead());
        Assert.assertEquals(3, gameState.getTickToEventsMap().get(tick).size());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWERTARGETSATTACKER, gameState.getTickToEventsMap().get(tick).get(0).getType());
        Assert.assertEquals(GameEvent.EVENT_TYPE_ATTACKERDIED, gameState.getTickToEventsMap().get(tick).get(1).getType());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWERLOSTTARGET, gameState.getTickToEventsMap().get(tick).get(2).getType());

        Assert.assertEquals(0, gameState.getAttackers().size());
    }


}
