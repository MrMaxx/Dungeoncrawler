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

        LOGGER.debug("START AttackerSucceededProcessor => tickInMilliseconds = "+tickInMilliseconds);
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

                        gameState.getEvents().add(event);

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

        }catch(IllegalArgumentException iae){
            LOGGER.error(gameState.getCheckPointToDungeonNodeMap());
            throw new RuntimeException(iae);
        }
        LOGGER.debug("END AttackerSucceededProcessor => tickInMilliseconds = "+tickInMilliseconds);

    }


    private static void moveAttacker(Attacker attacker, int tickInMilliseconds){

        if(attacker.getNextNodeVisit() == null){ return; }

        if(tickInMilliseconds == attacker.getNextNodeVisit().getVisitTime()){
            attacker.moveTo(attacker.getNextNodeVisit().getCoordinate());
            attacker.setLastDungeonNodeVisit(attacker.getNextNodeVisit(), attacker.getLastDungeonNodeIndex()+1);
            attacker.setNextNodeVisit(null);
            return;
        }

        attacker.moveTo(
                attacker.getLastNodeVisit().getCurrentTransitCoordinate(
                        attacker.getNextNodeVisit(), tickInMilliseconds));
    }

    private static List<GameEvent> checkForMoveFromToEvents(Attacker attacker, GameState gameState, int tickInMilliseconds){

        List<GameEvent> result = new LinkedList<GameEvent>();
        if(attacker.getNextNodeVisit() == null ){

            int nextNodeIndex = Integer.valueOf(attacker.getLastDungeonNodeIndex()+1);
            DungeonNode nextNode = gameState.getCheckPointToDungeonNodeMap().get(nextNodeIndex);

            // if nextNode is null the Attacker reached the last DungeonNode and thus succeeds
            if(nextNode == null){return result;}
            Coordinate nextCoordinate = new Coordinate(nextNode.getX(), nextNode.getY());

            int nextVisitTime = attacker.getLastNodeVisit().getTransitTimeInMillis(nextCoordinate, attacker.getSpeed()) +
                    attacker.getLastNodeVisit().getVisitTime();

            NodeVisit nextNodeVisit = new NodeVisit(
                    nextCoordinate,
                    nextVisitTime);
            attacker.setNextNodeVisit(nextNodeVisit);


            MoveFromTo moveEvent = new MoveFromTo(attacker.getId());
            moveEvent.setTime(attacker.getLastNodeVisit().getVisitTime());
            moveEvent.setStartingCoordinate(attacker.getLastNodeVisit().getCoordinate());
            moveEvent.setEndsAt(nextNodeVisit.getVisitTime());
            moveEvent.setEndingCoordinate(nextNodeVisit.getCoordinate());

            result.add(moveEvent);
        }

        return result;
    }

}
