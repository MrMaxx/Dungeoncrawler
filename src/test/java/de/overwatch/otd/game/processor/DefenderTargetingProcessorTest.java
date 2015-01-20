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


public class DefenderTargetingProcessorTest extends GameTest{

    GameState gameState;
    Turret turret;
    Attacker attacker;

    @Before
    public void init(){
        gameState = defaultGameState();

        turret = createTurret(1, defaultTowerBlueprint(), createConstructionSite(1, 80, 80));
        attacker = createAttacker(1, defaultAttackerBlueprint(), new Coordinate(80,120), new NodeVisit(new Coordinate(80,120), 1000));

        gameState.getAttackers().add(attacker);
        gameState.getTurrets().add(turret);
    }

    @Test
    public void testFirstHit() throws Exception{
        int oldHealth = attacker.getHealth();
        DefenderTargetingProcessor.process(gameState, 100);

        Assert.assertEquals(oldHealth - turret.getDamage(), attacker.getHealth());
        Assert.assertEquals(1, gameState.getEvents().size());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWERTARGETSATTACKER, gameState.getEvents().get(0).getType());
    }

    @Test
    public void testKilling() throws Exception{
        int count = (int)Math.ceil(attacker.getHealth() / turret.getDamage());
        for(int i = 0; i<count; i++){
            DefenderTargetingProcessor.process(gameState, 100+(i*turret.getTimeToReload()));
        }

        Assert.assertTrue(attacker.isDead());
        Assert.assertEquals(3, gameState.getEvents().size());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWERTARGETSATTACKER, gameState.getEvents().get(0).getType());
        Assert.assertEquals(GameEvent.EVENT_TYPE_ATTACKERDIED, gameState.getEvents().get(1).getType());
        Assert.assertEquals(GameEvent.EVENT_TYPE_TOWERLOSTTARGET, gameState.getEvents().get(2).getType());

        Assert.assertEquals(0, gameState.getAttackers().size());
    }


}
