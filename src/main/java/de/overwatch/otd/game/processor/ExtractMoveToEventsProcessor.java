package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.MoveFromTo;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.NodeVisit;

import java.util.List;

public class ExtractMoveToEventsProcessor {

    public static void process(GameState gameState){

        processAttackers(gameState.getAttackers(), gameState);
        processAttackers(gameState.getDeadAttackers(), gameState);

    }

    private static void processAttackers(List<Attacker> attackers, GameState gameState){

        for(Attacker attacker: attackers){
            NodeVisit lastVisit = null;
            for(NodeVisit nodeVisit: attacker.getNodeVisits()){

                if(lastVisit == null){
                    lastVisit = nodeVisit;
                    continue;
                }

                MoveFromTo moveEvent = new MoveFromTo(attacker.getId());
                moveEvent.setTime(lastVisit.getVisitTime());
                moveEvent.setStartingCoordinate(lastVisit.getCoordinate());
                moveEvent.setEndsAt(nodeVisit.getVisitTime());
                moveEvent.setEndingCoordinate(nodeVisit.getCoordinate());

                gameState.addEvent(lastVisit.getVisitTime(), moveEvent);

                lastVisit = nodeVisit;
            }

        }


    }


}
