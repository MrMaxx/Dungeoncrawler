package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.GameState;
import de.overwatch.otd.game.events.DefenderSpawned;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.DefenderSpawn;

import java.util.LinkedList;
import java.util.List;

public class DefenderSpawnProcessor {


    public static List<GameEvent> process(GameState gameState, int tickInMilliseconds){

        List<GameEvent> events = new LinkedList<GameEvent>();

        for(DefenderSpawn spawn : gameState.getDefenderSpawnes()){

            if( spawn.getExecuteAfterMillis() == tickInMilliseconds ){
                DefenderSpawned event = new DefenderSpawned();
                event.setId(spawn.getTurret().getId());
                event.setTowerType(spawn.getTurret().getType());
                event.setTime(tickInMilliseconds);

                events.add(event);
            }

        }

        return events;
    }


}
