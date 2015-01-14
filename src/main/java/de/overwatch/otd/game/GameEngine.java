package de.overwatch.otd.game;

import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.Spawn;
import de.overwatch.otd.game.processor.SpawnProcessor;

import java.util.LinkedList;
import java.util.List;


/**
 *  Just remember...this is a prototype...non optimized ;)
 */
public class GameEngine {

    private int milliSeconds;

    private List<Spawn> spawnes;
    private List<Attacker> attackers;
    private List<Tower> towers;

    private List<GameEvent> gameEvents = new LinkedList<GameEvent>();

    public GameEngine(List<Spawn> spawnes, List<Tower> towers) {
        this.spawnes = spawnes;
        this.towers = towers;
    }

    public List<GameEvent> processGame(){

        /* As long as there are Attackers on the Board, and we do not exceed 10 Minutes we continue */
        while( ! attackers.isEmpty() && milliSeconds < 600000 ){

            gameEvents.addAll(SpawnProcessor.process(spawnes, milliSeconds));



        }


        return gameEvents;
    }

}
