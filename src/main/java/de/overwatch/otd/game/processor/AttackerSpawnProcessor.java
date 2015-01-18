package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.AttackerSpawned;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Attacker;
import de.overwatch.otd.game.model.AttackerSpawn;

import java.util.LinkedList;
import java.util.List;

public class AttackerSpawnProcessor {


    public static List<GameEvent> process(GameState gameState, int tickInMilliseconds){

        List<GameEvent> events = new LinkedList<GameEvent>();

        for(AttackerSpawn spawn : gameState.getAttackerSpawnes()){

            if( spawn.getExecuteAfterMillis() == tickInMilliseconds ){
                Attacker attacker = spawn.getAttacker();
                AttackerSpawned event = new AttackerSpawned();
                event.setId(attacker.getId());
                event.setAttackerType(attacker.getType());
                event.setTime(tickInMilliseconds);

                events.add(event);

                gameState.getAttackers().add(spawn.getAttacker());
            }
        }

        return events;
    }


}
