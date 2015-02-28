package de.overwatch.otd.game;


import com.google.common.base.Function;
import com.google.common.collect.Maps;
import de.overwatch.otd.domain.attack.*;
import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.game.model.*;
import de.overwatch.otd.game.util.IdGenerator;
import de.overwatch.otd.game.model.NodeVisit;

import java.util.*;

public class AttackerSpawnBuilder {

    private Map<Integer, AttackerBlueprint> idToAttackerBlueprintMap;
    private Map<Integer, WaveBlueprint> idToWaveBlueprintMap;
    private Map<Integer,DungeonNode> checkPointToDungeonNodeMap;

    private AttackForce attackForce;
    private IdGenerator idGenerator;

    public AttackerSpawnBuilder(AttackForce attackForce,
                                AttackForcePattern attackForcePattern,
                                Map<Integer,AttackerBlueprint> idToAttackerBlueprintMap,
                                Map<Integer,DungeonNode> checkPointToDungeonNodeMap,
                                IdGenerator idGenerator) {
        this.idToAttackerBlueprintMap = idToAttackerBlueprintMap;
        this.checkPointToDungeonNodeMap = checkPointToDungeonNodeMap;
        this.attackForce = attackForce;
        this.idGenerator = idGenerator;

        idToWaveBlueprintMap = Maps.uniqueIndex(attackForcePattern.getWaveBlueprints(), new Function<WaveBlueprint, Integer>() {
            public Integer apply(WaveBlueprint from) {
                return from.getId();
            }
        });
    }

    public List<AttackerSpawn> build(){

        List<AttackerSpawn> result = new LinkedList<AttackerSpawn>();

        for(Wave wave : attackForce.getWaves()){

            WaveBlueprint waveBlueprint = idToWaveBlueprintMap.get(wave.getWaveBlueprintId());

            AttackerBlueprint attackerBlueprint = idToAttackerBlueprintMap.get(wave.getAttackerBlueprintId());;

            int unitCount = (int)(waveBlueprint.getSlots() / attackerBlueprint.getSlots());

            for(int i = 0; i < waveBlueprint.getSlots(); i++){

                // we omit all slots that are already filled
                if(i%attackerBlueprint.getSlots()!=0 || unitCount < i / (float)attackerBlueprint.getSlots()){
                    continue;
                }

                Attacker attacker = new Attacker(
                        idGenerator.getNextId(),
                        attackerBlueprint);

                DungeonNode start = checkPointToDungeonNodeMap.get(Integer.valueOf(0));

                int spawnsAt = waveBlueprint.getDispatchesAfter() + (waveBlueprint.getDelayBetweenSpawns()*i);

                attacker.moveTo(new Coordinate(start.getX(), start.getY()));
                attacker.setLastDungeonNodeVisit(
                        new NodeVisit(new Coordinate(start.getX(), start.getY()), spawnsAt), 0);
                result.add(
                        new AttackerSpawn(
                                spawnsAt, attacker));
            }
        }

        return result;
    }
}
