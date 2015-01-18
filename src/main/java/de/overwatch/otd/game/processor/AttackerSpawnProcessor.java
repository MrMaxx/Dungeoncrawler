package de.overwatch.otd.game.processor;


import de.overwatch.otd.game.events.AttackerSpawned;
import de.overwatch.otd.game.events.GameEvent;
import de.overwatch.otd.game.model.AttackerSpawn;

import java.util.LinkedList;
import java.util.List;

public class AttackerSpawnProcessor {


    public static List<GameEvent> process(List<AttackerSpawn> spawnes, int tickInMilliseconds){

        List<GameEvent> events = new LinkedList<GameEvent>();

        for(AttackerSpawn spawn : spawnes){

            if( spawn.getExecuteAfterMillis() == tickInMilliseconds ){
                AttackerSpawned event = new AttackerSpawned();
                event.setId(spawn.getAttacker().getId());
                event.setAttackerType(spawn.getAttacker().getType());
                event.setTime(tickInMilliseconds);

                events.add(event);
            }

        }

        return events;
    }


}
