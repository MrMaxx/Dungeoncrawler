package de.overwatch.otd.game.processor;


import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.AttackerSucceeded;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.events.MoveFromTo;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Coordinate;
import de.overwatch.otd.game.model.NodeVisit;
import de.overwatch.otd.game.model.Turret;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class AttackerSucceededProcessor {

    private static final Logger LOGGER = Logger.getLogger(AttackerSucceededProcessor.class);

    public static void process(GameState gameState, int tickInMilliseconds){

        //LOGGER.debug("START AttackerSucceededProcessor => tickInMilliseconds = "+tickInMilliseconds);
        try {
            List<Attacker> succeededAttackers = new LinkedList<Attacker>();
            for (Attacker attacker : gameState.getAttackers()) {

                /*
                        the MoveToProcessor leaves Attackers with a blank NextNodeVisit if they have reached the
                        end of the Dungeon.
                 */
                if(attacker.getNextNodeVisit() == null && attacker.getLastDungeonNodeIndex() > 0 ) {

                    int nextNodeIndex = Integer.valueOf(attacker.getLastDungeonNodeIndex() + 1);
                    DungeonNode nextNode = gameState.getCheckPointToDungeonNodeMap().get(nextNodeIndex);

                    if(nextNode==null){
                        succeededAttackers.add(attacker);

                        AttackerSucceeded event = new AttackerSucceeded(attacker.getId());
                        event.setTime(tickInMilliseconds);
                        event.setX(attacker.getCoordinate().getX());
                        event.setY(attacker.getCoordinate().getY());

                        gameState.addEvent(tickInMilliseconds, event);

                        gameState.increaseAttackerScore(attacker.getPrice());

                        for(Turret turret : attacker.getBeingTargetedBy()){
                            if( turret.getCurrentTarget().equals(attacker) ){
                                turret.setCurrentTarget(null);
                            }

                        }
                        attacker.getBeingTargetedBy().clear();
                    }

                }

            }

            gameState.getAttackers().removeAll(succeededAttackers);
            gameState.getDeadAttackers().addAll(succeededAttackers);

        }catch(IllegalArgumentException iae){
            LOGGER.error(gameState.getCheckPointToDungeonNodeMap());
            throw new RuntimeException(iae);
        }
        //LOGGER.debug("END AttackerSucceededProcessor => tickInMilliseconds = "+tickInMilliseconds);

    }

}
