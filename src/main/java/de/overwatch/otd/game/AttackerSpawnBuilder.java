package de.overwatch.otd.game;


import com.google.common.base.Function;
import com.google.common.collect.Maps;
import de.overwatch.otd.domain.Fight;
import de.overwatch.otd.domain.attack.*;
import de.overwatch.otd.domain.defend.DungeonNode;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.model.*;
import de.overwatch.otd.game.util.IdGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        int currentCheckPoint = 0;
        DungeonNode startNode = null;

        while( startNode == null && currentCheckPoint < 20 ){
            startNode = checkPointToDungeonNodeMap.get(Integer.valueOf(currentCheckPoint));
            currentCheckPoint++;
        }
        if(startNode == null){
            throw new IllegalStateException("There is no DungeonNode with a checkPoint < 20 for this Dungeon.");
        }

        for(Wave wave : attackForce.getWaves()){

            WaveBlueprint waveBlueprint = idToWaveBlueprintMap.get(wave.getWaveBlueprintId());

            for(int i = 0; i < waveBlueprint.getSlots(); i++){

                Attacker attacker = new Attacker(
                        idGenerator.getNextId(),
                        idToAttackerBlueprintMap.get(wave.getAttackerBlueprintId()),
                        new Coordinate(startNode.getX(), startNode.getY()));

                result.add(
                        new AttackerSpawn(
                                waveBlueprint.getDispatchesAfter()+(waveBlueprint.getDelayBetweenSpawns()*i),
                                attacker));
            }
        }

        return result;
    }
}
