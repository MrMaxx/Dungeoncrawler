package de.overwatch.otd.game;


import com.google.common.base.Function;
import com.google.common.collect.Maps;
import de.overwatch.otd.domain.attack.*;
import de.overwatch.otd.domain.defend.ConstructionSite;
import de.overwatch.otd.domain.defend.Dungeon;
import de.overwatch.otd.domain.defend.Tower;
import de.overwatch.otd.domain.defend.TowerBlueprint;
import de.overwatch.otd.game.model.*;
import de.overwatch.otd.game.util.IdGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefenderSpawnBuilder {

    private Map<Integer, TowerBlueprint> idToTowerBlueprintMap;
    private Map<Integer, ConstructionSite> idToConstructionSiteMap;
    private Dungeon dungeon;
    private IdGenerator idGenerator;

    public DefenderSpawnBuilder(Dungeon dungeon,
                                Map<Integer, TowerBlueprint> idToTowerBlueprintMap,
                                Map<Integer, ConstructionSite> idToConstructionSiteMap,
                                IdGenerator idGenerator) {
        this.idToTowerBlueprintMap = idToTowerBlueprintMap;
        this.dungeon = dungeon;
        this.idGenerator = idGenerator;
        this.idToConstructionSiteMap = idToConstructionSiteMap;
    }

    public List<DefenderSpawn> build(){

        List<DefenderSpawn> result = new LinkedList<DefenderSpawn>();

        for(Tower tower : dungeon.getTowers()){
            Turret turret = new Turret(
                    idGenerator.getNextId(),
                    idToTowerBlueprintMap.get(tower.getTowerBlueprintId()),
                    idToConstructionSiteMap.get(tower.getConstructionSiteId()));

            result.add(new DefenderSpawn(0, turret));
        }

        return result;
    }
}
