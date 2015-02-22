package de.overwatch.otd.game.processor;


import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.events.MoveFromTo;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Coordinate;
import de.overwatch.otd.game.model.NodeVisit;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class MoveToProcessor {

    private static final Logger LOGGER = Logger.getLogger(MoveToProcessor.class);

    public static void process(GameState gameState, int tickInMilliseconds){

        //LOGGER.debug("START MoveToProcessor => tickInMilliseconds = "+tickInMilliseconds);
        try {
            for (Attacker attacker : gameState.getAttackers()) {
                //LOGGER.debug("Processing Attacker => " + attacker);
                // 1: MOVE FORWARD
                moveAttacker(attacker, tickInMilliseconds);
                // 2. CHECK IF WE NEED TO PROGRESS ON DUNGEONNODE FURTHER
                checkForNewMoves(attacker, gameState, tickInMilliseconds);
            }
        }catch(IllegalArgumentException iae){
            LOGGER.error(gameState.getCheckPointToDungeonNodeMap());
            throw new RuntimeException(iae);
        }
        //LOGGER.debug("END MoveToProcessor => tickInMilliseconds = "+tickInMilliseconds);

    }


    private static void moveAttacker(Attacker attacker, int tickInMilliseconds){

        if(attacker.getNextNodeVisit() == null){ return; }

        if(tickInMilliseconds == attacker.getNextNodeVisit().getVisitTime()){
            LOGGER.debug("### NEXTNODEs VISITTIME has been REACHED Attacker => " + attacker);
            attacker.moveTo(attacker.getNextNodeVisit().getCoordinate());
            attacker.setLastDungeonNodeVisit(attacker.getNextNodeVisit(), attacker.getLastDungeonNodeIndex()+1);
            attacker.setNextNodeVisit(null);
            return;
        }

        attacker.moveTo(
                attacker.getLastNodeVisit().getCurrentTransitCoordinate(
                        attacker.getNextNodeVisit(), tickInMilliseconds));
    }

    private static void checkForNewMoves(Attacker attacker, GameState gameState, int tickInMilliseconds){

        if(attacker.getNextNodeVisit() == null ){

            int nextNodeIndex = Integer.valueOf(attacker.getLastDungeonNodeIndex()+1);
            DungeonNode nextNode = gameState.getCheckPointToDungeonNodeMap().get(nextNodeIndex);

            /*
                    if nextNode is null the Attacker reached the last DungeonNode
                    it will be picked up by the AttackerSucceededProcessor
             */
            if(nextNode == null){return;}
            Coordinate nextCoordinate = new Coordinate(nextNode.getX(), nextNode.getY());

            int nextVisitTime = attacker.getLastNodeVisit().getTransitTimeInMillis(nextCoordinate, attacker.getSpeed()) +
                    attacker.getLastNodeVisit().getVisitTime();

            NodeVisit nextNodeVisit = new NodeVisit(
                    nextCoordinate,
                    nextVisitTime);
            attacker.setNextNodeVisit(nextNodeVisit);
        }
    }

}
