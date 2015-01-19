package de.overwatch.otd.game;

import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.AttackerSpawn;
import de.overwatch.otd.game.model.DefenderSpawn;
import de.overwatch.otd.game.processor.AttackerSpawnProcessor;
import de.overwatch.otd.game.processor.AttackerSucceededProcessor;
import de.overwatch.otd.game.processor.DefenderSpawnProcessor;
import de.overwatch.otd.game.processor.MoveToProcessor;

import java.util.LinkedList;
import java.util.List;


/**
 *  Just remember...this is a prototype...non optimized ;)
 */
public class GameEngine {

    private int milliSeconds;

    private GameState gameState;

    private List<GameEvent> gameEvents = new LinkedList<GameEvent>();

    public GameEngine(GameState gameState) {
        this.gameState = gameState;
    }

    public List<GameEvent> processGame(){
        milliSeconds = 0;
        /* a Fight has to be finished in 10 Minutes */
        while( milliSeconds < 600000 ){

            gameEvents.addAll(DefenderSpawnProcessor.process(gameState, milliSeconds));
            gameEvents.addAll(AttackerSpawnProcessor.process(gameState, milliSeconds));
            gameEvents.addAll(MoveToProcessor.process(gameState, milliSeconds));



            gameEvents.addAll(AttackerSucceededProcessor.process(gameState, milliSeconds));
            milliSeconds++;
        }


        return gameEvents;
    }

}
