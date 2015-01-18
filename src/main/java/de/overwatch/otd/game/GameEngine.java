package de.overwatch.otd.game;

import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.AttackerSpawn;
import de.overwatch.otd.game.model.DefenderSpawn;
import de.overwatch.otd.game.processor.AttackerSpawnProcessor;
import de.overwatch.otd.game.processor.DefenderSpawnProcessor;

import java.util.LinkedList;
import java.util.List;


/**
 *  Just remember...this is a prototype...non optimized ;)
 */
public class GameEngine {

    private int milliSeconds;

    private List<AttackerSpawn> attackerSpawnes;
    private List<DefenderSpawn> defenderSpawnes;
    private List<Attacker> attackers;
    private List<Tower> towers;

    private List<GameEvent> gameEvents = new LinkedList<GameEvent>();

    public GameEngine(List<AttackerSpawn> spawnes, List<DefenderSpawn> defenderSpawnes) {
        this.attackerSpawnes = spawnes;
        this.defenderSpawnes = defenderSpawnes;
    }




    public List<GameEvent> processGame(){
        milliSeconds = 0;
        /* a Fight has to be finished in 10 Minutes */
        while( milliSeconds < 600000 ){

            gameEvents.addAll(DefenderSpawnProcessor.process(defenderSpawnes, milliSeconds));
            gameEvents.addAll(AttackerSpawnProcessor.process(attackerSpawnes, milliSeconds));



            milliSeconds++;
        }


        return gameEvents;
    }

}
