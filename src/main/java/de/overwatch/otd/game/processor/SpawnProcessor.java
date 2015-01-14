package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.events.AttackerSpawned;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.Spawn;

import java.util.LinkedList;
import java.util.List;

/**
 * Processes if there are any SpawnEvents are taking place in this round/millisecond of the game
 */
public class SpawnProcessor {


    public static List<GameEvent> process(List<Spawn> spawnes, int tickInMilliseconds){

        List<GameEvent> events = new LinkedList<GameEvent>();

        for(Spawn spawn : spawnes){

            if( spawn.getExecuteAfterMillis() == tickInMilliseconds ){
                AttackerSpawned event = new AttackerSpawned();
                event.setId(spawn.getAttacker().getId());
                event.setType(spawn.getAttacker().getType());
                event.setTime(tickInMilliseconds);

                events.add(event);
            }

        }

        return events;
    }


}
