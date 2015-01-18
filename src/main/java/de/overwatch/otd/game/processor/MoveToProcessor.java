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

    public static List<GameEvent> process(GameState gameState, int tickInMilliseconds){

        List<GameEvent> events = new LinkedList<GameEvent>();

        LOGGER.debug("START MoveToProcessor => tickInMilliseconds = "+tickInMilliseconds);
        try {
            for (Attacker attacker : gameState.getAttackers()) {
                LOGGER.debug("Processing Attacker => " + attacker);
                // 1: MOVE FORWARD
                moveAttacker(attacker, tickInMilliseconds);
                // 2: CHECK IF NEW MoveFromTo-Event NEEDS TO BE GENERATED
                events.addAll(checkForMoveFromToEvents(attacker, gameState, tickInMilliseconds));
            }
        }catch(IllegalArgumentException iae){
            LOGGER.debug(gameState.getCheckPointToDungeonNodeMap());
            throw new RuntimeException(iae);
        }
        LOGGER.debug("END MoveToProcessor => tickInMilliseconds = "+tickInMilliseconds);

        return events;
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

            // Todo: continue here...we need an AttackerSucceededEvent/Processor that takes Attackers out if they reach the last DungeonNode
            if(nextNode == null){return result;}
            Coordinate nextCoordinate = new Coordinate(nextNode.getX(), nextNode.getY());

            int nextVisitTime = attacker.getLastNodeVisit().getTransitTimeInMillis(nextCoordinate, attacker.getSpeed()) +
                    attacker.getLastNodeVisit().getVisitTime();

            NodeVisit nextNodeVisit = new NodeVisit(
                    nextCoordinate,
                    nextVisitTime);
            attacker.setNextNodeVisit(nextNodeVisit);


            MoveFromTo moveEvent = new MoveFromTo();
            moveEvent.setAttackerId(attacker.getId());
            moveEvent.setStartsAt(attacker.getLastNodeVisit().getVisitTime());
            moveEvent.setStartingCoordinate(attacker.getLastNodeVisit().getCoordinate());
            moveEvent.setEndsAt(nextNodeVisit.getVisitTime());
            moveEvent.setEndingCoordinate(nextNodeVisit.getCoordinate());

            result.add(moveEvent);
        }

        return result;
    }

}
