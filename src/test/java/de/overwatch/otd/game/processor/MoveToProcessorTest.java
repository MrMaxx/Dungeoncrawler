package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.GameTest;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Coordinate;
import de.overwatch.otd.game.model.NodeVisit;
import de.overwatch.otd.game.model.Turret;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MoveToProcessorTest extends GameTest{

    GameState gameState;
    Turret turret;
    Attacker attacker;

    @Before
    public void init(){
        gameState = defaultGameState();

        turret = createTurret(1, effectTowerBlueprint(), createConstructionSite(1, 100, 80));

        Coordinate startCoordinate = new Coordinate(80,80);
        attacker = createAttacker(1, defaultAttackerBlueprint(), startCoordinate, new NodeVisit(startCoordinate, 0));

        gameState.getAttackers().add(attacker);
        gameState.getTurrets().add(turret);
    }

    @Test
    public void testMovement() throws Exception{

        for(int i = 0; i<60000; i++){

            // 3. calculate the movements of the Attackers
            MoveToProcessor.process(gameState, i);
            AttackerSucceededProcessor.process(gameState, i);

        }

        ExtractMoveToEventsProcessor.process(gameState);

        Assert.assertEquals(3, attacker.getNodeVisits().size());

        Assert.assertEquals(3, gameState.getTickToEventsMap().values().size());
    }


}
