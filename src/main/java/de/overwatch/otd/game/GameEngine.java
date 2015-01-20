package de.overwatch.otd.game;

import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.AttackerSpawn;
import de.overwatch.otd.game.model.DefenderSpawn;
import de.overwatch.otd.game.processor.*;

import java.util.LinkedList;
import java.util.List;


/**
 *  Just remember...this is a prototype...non optimized ;)
 */
public class GameEngine {

    private int milliSeconds;

    private GameState gameState;

    public GameEngine(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState processGame(){
        milliSeconds = 0;
        /* a Fight has to be finished in 10 Minutes */
        while( milliSeconds < 600000 ){

            // 1. lets see if some Turrets are spawning
            DefenderSpawnProcessor.process(gameState, milliSeconds);
            // 2. lets see if some Attackers are spawning
            AttackerSpawnProcessor.process(gameState, milliSeconds);
            // 3. calculate the movements of the Attackers
            MoveToProcessor.process(gameState, milliSeconds);
            // 4. Turrets need to inflict damage...that is what they were build for
            DefenderTargetingProcessor.process(gameState,milliSeconds);
            // 5. Sometimes Attackers run through the whole Dungeon ands succeed
            AttackerSucceededProcessor.process(gameState, milliSeconds);

            milliSeconds++;
        }


        return gameState;
    }

}
